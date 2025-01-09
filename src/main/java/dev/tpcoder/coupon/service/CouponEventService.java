package dev.tpcoder.coupon.service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.github.javafaker.Faker;
import dev.tpcoder.coupon.CouponClaimed;

@Service
public class CouponEventService {

    private final KafkaTemplate<UUID, CouponClaimed> kafkaTemplate;
    private final Faker faker = new Faker();

    public CouponEventService(final KafkaTemplate<UUID, CouponClaimed> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 1000) // Run every 1 second
    public void generateAndPublishCouponClaimedEvent() {
        final CouponClaimed event = CouponClaimed.builder().claimId(UUID.randomUUID())
                .couponId(faker.number().randomNumber(5, false)).firstName(faker.name().firstName())
                .lastName(faker.name().lastName()).merchantName(faker.company().name())
                .merchantId(UUID.randomUUID())
                .claimedOn(faker.date().past(10, TimeUnit.MINUTES).toInstant()).build();

        kafkaTemplate.send("coupon-claims", event.claimId(), event);
    }

    @KafkaListener(topics = "coupon-claims", groupId = "coupon-processor")
    public void consumeCouponClaimedEvent(final UUID claimId, final CouponClaimed event) {
        System.out.println("Received CouponClaimed event: " + event);
    }
}

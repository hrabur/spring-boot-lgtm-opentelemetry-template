package dev.tpcoder.coupon.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic couponClaimedTopic() {
        return TopicBuilder.name("coupon-claims").partitions(3).replicas(1).build();
    }
}

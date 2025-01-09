package dev.tpcoder.coupon;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;

@Builder
public record CouponClaimed(UUID claimId, Long couponId, String firstName, String lastName,
                String merchantName, UUID merchantId, Instant claimedOn) {

}

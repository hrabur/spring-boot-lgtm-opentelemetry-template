package dev.tpcoder.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class CouponApplication {

	public static void main(final String[] args) {
		SpringApplication.run(CouponApplication.class, args);
	}

}

package com.rizwan.product_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@FeignClient(name = "Cop-service",url = "http://docker-coupenService:9099/coupens")

public interface CoupenClient {
    @GetMapping("/validate")
    Coupen getCouponByCode(@RequestParam("code") String code, @RequestParam("orderAmount") BigDecimal orderAmount);

}

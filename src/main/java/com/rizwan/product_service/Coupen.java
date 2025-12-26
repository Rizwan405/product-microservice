package com.rizwan.product_service;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Coupen(String code, BigDecimal discount, String discountType, Integer usedCount, Integer usageLimit, BigDecimal minOrderAmount, boolean active,
                     LocalDate expiryDate) {
}

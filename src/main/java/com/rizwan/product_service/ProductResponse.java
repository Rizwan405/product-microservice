package com.rizwan.product_service;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(String name, String description, BigDecimal price,BigDecimal discountPrice,String code) {
}

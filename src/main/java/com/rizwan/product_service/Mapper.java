package com.rizwan.product_service;

import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class Mapper {
//
    public Model toModel(ProductRequest request){
        return Model.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())

                .build();
    }

}

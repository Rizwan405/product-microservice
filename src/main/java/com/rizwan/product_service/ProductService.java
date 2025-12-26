package com.rizwan.product_service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final Mapper mapper;
    private final ProductRepo productRepo;
    private final CoupenClient coupenClient;

    public Model addProduct(ProductRequest request) {
        Model product = mapper.toModel(request);
        productRepo.save(product);
        return product;
    }
    public List<Model> getAllProducts(){
        return productRepo.findAll();
    }
    public void deleteProduct(Long productId){
        Model product = productRepo.findModelById(productId).orElseThrow(()-> new EntityNotFoundException("Product not found"));
        if(product!=null) productRepo.delete(product);
    }
    public ProductResponse getProductById(Long productId) {
        String code = "BLACKFRIDAY2025";
        LocalDate date = Instant.ofEpochMilli(System.currentTimeMillis())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        Model product = productRepo.findModelById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));


        Coupen coupon;
        try {
            coupon = coupenClient.getCouponByCode(code, product.getPrice());
        } catch (Exception ex) {
            // log error
            coupon = null; // no coupon applied if validation fails
        }

        BigDecimal discountedPrice = (coupon != null) ? product.getPrice().subtract(coupon.discount()) : product.getPrice();

        return new ProductResponse(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                discountedPrice,
                coupon != null ? coupon.code() : null
        );
    }
    public ProductResponse getProduct(Long productId){
        Model product = productRepo.findModelById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return new ProductResponse(product.getName(), product.getDescription(), product.getPrice(),product.getPrice(),null);
    }
}

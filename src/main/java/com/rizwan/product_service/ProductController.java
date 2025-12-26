package com.rizwan.product_service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")

public class ProductController {
    private final ProductService productService;
    @GetMapping("products")
    public ResponseEntity<String> getResponse(){
        return ResponseEntity.status(HttpStatus.OK).body("Product Service is running");
    }
    @GetMapping("products/getAllProducts")
    public ResponseEntity<List<Model>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }
    //    add product
    @PostMapping("products/addProduct")
    public ResponseEntity<Model> addProduct(@RequestBody  ProductRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(request));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId){
        try {
             productService.deleteProduct(productId);
             return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
         }catch (Exception e){
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting product");
         }
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long productId){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(productId));
    }
    @GetMapping("/products/id/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") Long productId){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(productId));
    }
}

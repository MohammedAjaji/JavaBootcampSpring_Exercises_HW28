package com.example.spring_homework28.Controller;

import com.example.spring_homework28.Model.MyUser;
import com.example.spring_homework28.Model.Order;
import com.example.spring_homework28.Model.Product;
import com.example.spring_homework28.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getProduct(){
        List<Product> products = productService.getProducts();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return ResponseEntity.status(200).body("Product Added");
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity updateProduct(@RequestBody Product product, @PathVariable Integer productId){
        productService.updateProduct(product, productId);
        return ResponseEntity.status(200).body("Product Updated");
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Integer productId){
        productService.deleteProduct(productId);
        return ResponseEntity.status(200).body("Product Deleted");
    }

    @GetMapping("get-id/{productId}")
    public ResponseEntity getProductById(@PathVariable Integer productId){
        Product product = productService.getProductId(productId);
        return ResponseEntity.status(200).body(product);
    }


}

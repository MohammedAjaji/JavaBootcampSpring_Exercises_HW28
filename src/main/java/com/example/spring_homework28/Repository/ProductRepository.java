package com.example.spring_homework28.Repository;

import com.example.spring_homework28.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findProductById(Integer productId);
    Product findProductByName(String name);

}

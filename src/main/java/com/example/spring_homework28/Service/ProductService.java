package com.example.spring_homework28.Service;

import com.example.spring_homework28.ApiException.ApiException;
import com.example.spring_homework28.Model.MyUser;
import com.example.spring_homework28.Model.Order;
import com.example.spring_homework28.Model.Product;
import com.example.spring_homework28.Repository.OrderRepository;
import com.example.spring_homework28.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Product product, Integer productId) {
        Product oldProduct = productRepository.findProductById(productId);
        if (oldProduct == null){
            throw new ApiException("Product Not Found");
        }

        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        productRepository.save(oldProduct);
    }

    public void deleteProduct(Integer productId) {
        Product product = productRepository.findProductById(productId);
        if (product == null){
            throw new ApiException("Product Not Found");
        }

        List<Order> orders = orderRepository.findOrdersByProduct(product);
        for (int i = 0; i < orders.size(); i++) {
            orders.get(i).setProduct(null);
        }

        productRepository.delete(product);
    }

    public Product getProductId(Integer productId) {
        Product product = productRepository.findProductById(productId);
        if (product == null){
            throw new ApiException("Product Not found");
        }
        return product;
    }


}

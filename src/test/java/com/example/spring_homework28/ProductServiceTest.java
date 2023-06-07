package com.example.spring_homework28;


import com.example.spring_homework28.Model.MyUser;
import com.example.spring_homework28.Model.Order;
import com.example.spring_homework28.Model.Product;
import com.example.spring_homework28.Repository.OrderRepository;
import com.example.spring_homework28.Repository.ProductRepository;
import com.example.spring_homework28.Service.OrderService;
import com.example.spring_homework28.Service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;
    @Mock
    OrderRepository orderRepository;
    @Mock
    ProductRepository productRepository;

    Order order, order1,order2,order3;
    Product product1, product2, product3;
    MyUser user;

    List<Product> productList;

    @BeforeEach
    void setUp(){

        user = new MyUser(1, "Rayed", "Mohammed@123", "ADMIN", null);
        product1 = new Product(null, "Book1", 10.0,null);
        product2 = new Product(null, "Book2", 10.0,null);
        product3 = new Product(null, "Book3", 10.0,null);


        order1 = new Order(null, 1, 10.0, new Date(), "new", user, product1);
        order2 = new Order(null, 2, 20.0, new Date(), "new", user, product1);
        order3 = new Order(null, 3, 30.0, new Date(), "new", user, product1);


        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

    }

    @Test
    public void getProducts(){
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> productList1 = productService.getProducts();
        Assertions.assertEquals(productList1,productList);
        Assertions.assertEquals(productList1.size(),productList.size());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void addProduct(){
        productService.addProduct(product1);
        verify(productRepository,times(1)).save(product1);

    }

    @Test
    public void updateProduct(){
        when(productRepository.findProductById(product1.getId())).thenReturn(product1);
        productService.updateProduct(product2,product1.getId());

        verify(productRepository,times(1)).findProductById(product1.getId());
        verify(productRepository,times(1)).save(product1);
    }

    @Test
    public void deleteProduct(){
        when(productRepository.findProductById(product1.getId())).thenReturn(product1);
        productService.deleteProduct(product1.getId());

        verify(productRepository,times(1)).findProductById(product1.getId());
        verify(productRepository,times(1)).delete(product1);
    }

}

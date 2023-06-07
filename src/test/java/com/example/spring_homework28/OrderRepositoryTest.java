package com.example.spring_homework28;


import com.example.spring_homework28.Model.MyUser;
import com.example.spring_homework28.Model.Order;
import com.example.spring_homework28.Model.Product;
import com.example.spring_homework28.Repository.MyUserRepository;
import com.example.spring_homework28.Repository.OrderRepository;
import com.example.spring_homework28.Repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MyUserRepository myUserRepository;

    @Autowired
    ProductRepository productRepository;

    Order order, order1,order2,order3;
    Product product;
    MyUser user;

    List<Order> orderList;

    @BeforeEach
    void setUp(){

        user = new MyUser(null, "Rayed", "Mohammed@123", "ADMIN", null);
        product = new Product(null, "Book", 10.0,null);

        order1 = new Order(null, 1, 10.0, new Date(), "new", user, product);
        order2 = new Order(null, 2, 20.0, new Date(), "new", user, product);
        order3 = new Order(null, 3, 30.0, new Date(), "new", user, product);
    }

    @Test
    public void findOrderById(){
        myUserRepository.save(user);
        productRepository.save(product);
        orderRepository.save(order1);

        order = orderRepository.findOrderById(order1.getId());
        Assertions.assertThat(order).isEqualTo(order1);
    }

    @Test
    public void findOrdersByUser(){
        myUserRepository.save(user);
        productRepository.save(product);
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

        orderList = orderRepository.findOrdersByUser(user);
        Assertions.assertThat(orderList.get(0).getUser().getId()).isEqualTo(user.getId());
    }

    @Test
    public void findOrdersByProduct(){
        myUserRepository.save(user);
        productRepository.save(product);
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

        orderList = orderRepository.findOrdersByProduct(product);
        Assertions.assertThat(orderList.get(0).getProduct().getId()).isEqualTo(product.getId());
    }

//    Order findOrderById(Integer orderId);
//    List<Order> findOrdersByUser(MyUser myUser);
//    List<Order> findOrdersByProduct(Product product);
}

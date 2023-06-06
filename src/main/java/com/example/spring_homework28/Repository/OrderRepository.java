package com.example.spring_homework28.Repository;

import com.example.spring_homework28.Model.MyUser;
import com.example.spring_homework28.Model.Order;
import com.example.spring_homework28.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {


    Order findOrderById(Integer orderId);
    List<Order> findOrdersByUser(MyUser myUser);

    List<Order> findOrdersByProduct(Product product);


}

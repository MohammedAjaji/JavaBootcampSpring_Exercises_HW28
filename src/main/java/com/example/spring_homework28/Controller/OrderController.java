package com.example.spring_homework28.Controller;


import com.example.spring_homework28.Model.MyUser;
import com.example.spring_homework28.Model.Order;
import com.example.spring_homework28.Model.OrderDTO;
import com.example.spring_homework28.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/get")
    public ResponseEntity getOrder(){
        List<Order> orders = orderService.getOrders();
        return ResponseEntity.status(200).body(orders);
    }

    @PostMapping("/add")
    public ResponseEntity addOrder(@AuthenticationPrincipal MyUser myUser, @RequestBody OrderDTO dto){
        orderService.addOrder(myUser, dto);
        return ResponseEntity.status(200).body("Order Added");
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity updateOrder(@AuthenticationPrincipal MyUser myUser, @RequestBody OrderDTO dto, @PathVariable Integer orderId){
        orderService.updateOrder(myUser.getId(), dto, orderId);
        return ResponseEntity.status(200).body("Order Updated");
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity deleteOrder(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer orderId){
        orderService.deleteOrder(myUser.getId(), orderId);
        return ResponseEntity.status(200).body("Order Deleted");
    }

    @PutMapping("change-status/{orderId}/{status}")
    public ResponseEntity changeStatus(@PathVariable Integer orderId, @PathVariable String status){
        orderService.changeStatus(orderId,status);
        return ResponseEntity.status(200).body("Status changed to " + status);
    }

    @GetMapping("get-user-orders")
    public ResponseEntity getUserOrders(@AuthenticationPrincipal MyUser myUser){
        List<Order> orders = orderService.getUserOrders(myUser);
        return ResponseEntity.status(200).body(orders);
    }

    @GetMapping("get-id/{orderId}")
    public ResponseEntity getOrderById(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer orderId){
        Order order = orderService.getOrderId(myUser,orderId);
        return ResponseEntity.status(200).body(order);
    }



}

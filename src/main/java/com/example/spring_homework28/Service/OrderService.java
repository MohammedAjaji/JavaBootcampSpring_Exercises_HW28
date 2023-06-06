package com.example.spring_homework28.Service;

import com.example.spring_homework28.ApiException.ApiException;
import com.example.spring_homework28.Model.MyUser;
import com.example.spring_homework28.Model.Order;
import com.example.spring_homework28.Model.OrderDTO;
import com.example.spring_homework28.Model.Product;
import com.example.spring_homework28.Repository.MyUserRepository;
import com.example.spring_homework28.Repository.OrderRepository;
import com.example.spring_homework28.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final MyUserRepository myUserRepository;


    public List<Order> getOrders() {
        return orderRepository.findAll();
    }



    public void addOrder(MyUser myUser, OrderDTO dto) {
        Product product = productRepository.findProductById(dto.getProductId());

        Order order = new Order();

        order.setStatus("new");
        order.setQuantity(dto.getQuantity());
        order.setDateReceived(new Date());
        order.setUser(myUser);
        order.setProduct(product);

        Double totalPrice = product.getPrice() * dto.getQuantity();
        order.setTotalPrice(totalPrice);


        orderRepository.save(order);
    }

    public void updateOrder(Integer userId, OrderDTO dto, Integer orderId) {
        Order oldOrder = orderRepository.findOrderById(orderId);
        if (oldOrder == null){
            throw new ApiException("Order Not Found");
        }

        if (!(userId.equals(oldOrder.getUser().getId()))){
            throw new ApiException("Not Authorized");
        }

        if (oldOrder.getStatus().equals("inProgress")){
            throw new ApiException("Cannot Update Order in Progress");
        }

        oldOrder.setQuantity(dto.getQuantity());
        Double totalPrice = oldOrder.getProduct().getPrice()* dto.getQuantity();
        oldOrder.setTotalPrice(totalPrice);

        orderRepository.save(oldOrder);
    }

    public void deleteOrder(Integer userId, Integer orderId) {
        Order order = orderRepository.findOrderById(orderId);
        if (order == null){
            throw new ApiException("Order Not Found");
        }

        if (order.getStatus().equals("inProgress")){
            throw new ApiException("Cannot delete Order in Progress");
        }

        if (!(userId.equals(order.getUser().getId()))){
            throw new ApiException("Not Authorized");
        }
        orderRepository.delete(order);
    }


    public void changeStatus(Integer orderId, String status) {
        Order order = orderRepository.findOrderById(orderId);
        if (order == null){
            throw new ApiException("Order Not Found");
        }
        order.setStatus(status);
        orderRepository.save(order);
    }

    public List<Order> getUserOrders(MyUser myUser) {
        List<Order> orders = orderRepository.findOrdersByUser(myUser);
        return orders;
    }

    public Order getOrderId(MyUser myUser,Integer orderId) {
        Order order =orderRepository.findOrderById(orderId);
        if (order == null){
            throw new ApiException("Order Not found");
        }

        if (!(myUser.getId().equals(order.getUser().getId()))){
            throw new ApiException("Not Authorized");
        }

        return order;
    }


}

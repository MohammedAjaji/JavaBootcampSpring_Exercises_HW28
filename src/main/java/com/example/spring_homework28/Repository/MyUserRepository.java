package com.example.spring_homework28.Repository;

import com.example.spring_homework28.Model.MyUser;
import com.example.spring_homework28.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser,Integer> {

    MyUser findMyUserByUsername(String username);
    MyUser findMyUserById(Integer userId);

    MyUser findMyUserByOrderSetContains(Order order);

}

package com.example.spring_day1.Repository;

import com.example.spring_day1.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<MyUser,Integer> {
    MyUser findMyUserByUsername(String username);

    MyUser findMyUserByUsernameAndPassword(String username, String  password);
}

package com.example.spring_homework28.Service;

import com.example.spring_homework28.ApiException.ApiException;
import com.example.spring_homework28.Model.MyUser;
import com.example.spring_homework28.Model.Order;
import com.example.spring_homework28.Model.Product;
import com.example.spring_homework28.Repository.MyUserRepository;
import com.example.spring_homework28.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

   private final MyUserRepository myUserRepository;
   private final OrderRepository orderRepository;

   public List<MyUser> getUsers(){
       return myUserRepository.findAll();
   }

   public void registerUser(MyUser myUser){
        String hash = new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hash);
        myUser.setRole("CUSTOMER");
        myUserRepository.save(myUser);
    }

    public void updateUserPassword(MyUser myUser, String password) {
        MyUser oldUser = myUserRepository.findMyUserById(myUser.getId());

        String hash = new BCryptPasswordEncoder().encode(password);
        oldUser.setPassword(hash);
        myUserRepository.save(oldUser);
    }

    public void updateUserUsername(MyUser myUser, String username) {
        MyUser oldUser = myUserRepository.findMyUserById(myUser.getId());

        oldUser.setUsername(username);
        myUserRepository.save(oldUser);
    }

    public void deleteUser(MyUser myUser) {

        List<Order> orders = orderRepository.findOrdersByUser(myUser);
        for (int i = 0; i < orders.size(); i++) {
            orders.get(i).setUser(null);
        }
        MyUser oldUser = myUserRepository.findMyUserById(myUser.getId());
        myUserRepository.delete(oldUser);
    }

    public MyUser getUserId(Integer userId) {
        MyUser myUser = myUserRepository.findMyUserById(userId);
        if (myUser == null){
            throw new ApiException("User Not found");
        }
        return myUser;
    }

}

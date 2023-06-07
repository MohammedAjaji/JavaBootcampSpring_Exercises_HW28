package com.example.spring_homework28;


import com.example.spring_homework28.Model.MyUser;
import com.example.spring_homework28.Repository.MyUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MyUserRepositoryTest {

    @Autowired
    MyUserRepository myUserRepository;

    MyUser user, user1, user2,user3;

    @BeforeEach
    void setUp(){

        user1 =new MyUser(null, "Rayed", "Mohammed@123", "ADMIN", null);
        user2 =new MyUser(null, "Saleh", "Mohammed@123", "CUSTOMER", null);
        user3 =new MyUser(null, "Abdullah", "Mohammed@123", "CUSTOMER", null);
    }

    @Test
    public void findMyUserByUsername(){
        myUserRepository.save(user1);
        user = myUserRepository.findMyUserByUsername(user1.getUsername());
        Assertions.assertThat(user).isEqualTo(user1);

    }

    @Test
    public void findMyUserById(){
        myUserRepository.save(user1);
        user = myUserRepository.findMyUserById(user1.getId());
        Assertions.assertThat(user).isEqualTo(user1);

    }
}

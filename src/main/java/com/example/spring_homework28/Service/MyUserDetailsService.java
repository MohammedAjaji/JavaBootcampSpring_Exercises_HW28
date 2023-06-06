package com.example.spring_homework28.Service;

import com.example.spring_homework28.Model.MyUser;
import com.example.spring_homework28.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MyUserRepository myUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUser myUser = myUserRepository.findMyUserByUsername(username);

        if (myUser == null){
            throw new UsernameNotFoundException("Wrong username or password");
        }

        return myUser;
    }
}

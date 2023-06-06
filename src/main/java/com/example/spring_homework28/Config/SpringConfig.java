package com.example.spring_homework28.Config;

import com.example.spring_homework28.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringConfig {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "api/v1/auth/register").permitAll()
                .requestMatchers(HttpMethod.GET,"api/v1/product/get").permitAll()
                .requestMatchers(HttpMethod.POST, "api/v1/auth/admin").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "api/v1/auth/customer").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/product/add",
                        "api/v1/product/update/{productId}",
                        "api/v1/product/delete/{productId}",
                        "api/v1/order/get",
                        "api/v1/order/change-status/{orderId}/{status}",
                        "api/v1/user/get").hasAuthority("ADMIN")
                .requestMatchers("api/v1/auth/login").hasAuthority("CUSTOMER")
                .anyRequest().hasAuthority("CUSTOMER")
                .and()
                .logout().logoutUrl("api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }
}

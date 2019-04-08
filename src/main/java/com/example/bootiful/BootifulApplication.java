package com.example.bootiful;

import com.example.bootiful.model.Customer;
import com.example.bootiful.repository.CustomerRepository;
import lombok.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class BootifulApplication {

//    @Bean
//    MapReactiveUserDetailsService users(){
//        return new MapReactiveUserDetailsService(User.withDefaultPasswordEncoder().username("user").password("pw").roles("roles").build());
//    }

    public static void main(String[] args) {
        SpringApplication.run(BootifulApplication.class, args);
    }
}
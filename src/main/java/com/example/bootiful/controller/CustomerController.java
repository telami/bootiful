package com.example.bootiful.controller;

import com.example.bootiful.model.Customer;
import com.example.bootiful.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/customers",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Customer> list() {
        return customerService.findAll().delayElements(Duration.ofSeconds(2));
    }

    @PostMapping("customer")
    public Flux<Customer> save(){
        Flux.just("红茶", "绿茶", "花茶")
                .flatMap(name -> customerService.save(new Customer(null, name)))
                .subscribe(System.out::println);
        return Flux.empty();
    }
}

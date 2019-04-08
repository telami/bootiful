package com.example.bootiful.config;

import com.example.bootiful.model.Customer;
import com.example.bootiful.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RouteConfig {

    private final CustomerRepository cr;

    @Autowired
    public RouteConfig(CustomerRepository cr) {
        this.cr = cr;
    }

    @Bean
    RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route(GET("/list"), serverRequest -> ok().body(cr.findAll(), Customer.class));
    }
}

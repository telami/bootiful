package com.example.bootiful;

import lombok.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class BootifulApplication {

    @Bean
    MapReactiveUserDetailsService users(){
        return new MapReactiveUserDetailsService(User.withDefaultPasswordEncoder().username("user").password("pw").roles("roles").build());
    }

    @Bean
    RouterFunction<ServerResponse> routes(CustomerRepository cr) {
        return RouterFunctions.route(GET("/customers"), serverRequest -> ok().body(cr.findAll(), Customer.class));
    }

    public static void main(String[] args) {
        SpringApplication.run(BootifulApplication.class, args);
    }

}

@Component
class DataWriter implements ApplicationRunner {

    private final CustomerRepository customerRepository;

    DataWriter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Flux.just("红茶", "绿茶", "花茶")
                .flatMap(name -> customerRepository.save(new Customer(null, name)))
                .subscribe(System.out::println);
    }
}

interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

}

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
class Customer {
    private String id, name;
}
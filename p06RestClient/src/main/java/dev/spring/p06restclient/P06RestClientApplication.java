package dev.spring.p06restclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class P06RestClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(P06RestClientApplication.class, args);

    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();

    }

}

//https://jsonplaceholder.typicode.com/

package dev.spring.p03springintro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:foo.properties")
public class P03SpringIntroApplication {

    public static void main(String[] args) {

        SpringApplication.run(P03SpringIntroApplication.class, args);
    }

}

package dev.spring.p10walletapp;

import dev.spring.p10walletapp.config.annotation.DeveloperInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@DeveloperInfo(
        expertise = DeveloperInfo.Expertise.JUNIOR,
        createdBy = "Tolga E",
        url = "https://github.com/tolgaeken",
        email = "tolga@e.com"
)
public class P10WalletAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(P10WalletAppApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

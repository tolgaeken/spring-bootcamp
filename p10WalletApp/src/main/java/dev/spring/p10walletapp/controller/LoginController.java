package dev.spring.p10walletapp.controller;

import dev.spring.p10walletapp.dto.LoginDto;
import dev.spring.p10walletapp.service.LoginService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private LoginService loginService;

    public LoginController(BCryptPasswordEncoder bCryptPasswordEncoder, LoginService loginService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.loginService = loginService;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody LoginDto loginDto) {
        loginDto.setPassword(bCryptPasswordEncoder.encode(loginDto.getPassword()));
        loginService.save(loginDto);
    }

}

package dev.spring.p08thymeleafdemo.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/api")
public class TemplateController {

    @Value("${developer.name}")
    private String developerName;

    @GetMapping("/hello")
    public String sayHello(Model model){
        model.addAttribute("developerName",developerName);
        model.addAttribute("serverTime", LocalDateTime.now());

        return "Hello";

    }
}

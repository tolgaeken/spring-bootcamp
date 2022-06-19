package dev.spring.p08thymeleafdemo.controller;

import dev.spring.p08thymeleafdemo.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/api")
public class EmployeeController {

    private List<Employee> employeeList;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @PostConstruct
    public void loadData(){
        employeeList = Arrays.asList(
                new Employee(atomicInteger.incrementAndGet(),"Ali Veli",36,3000),
                new Employee(atomicInteger.incrementAndGet(),"Ayse Kaya",26,2500),
                new Employee(atomicInteger.incrementAndGet(),"Hasan Huseyin",45,3500)
        );
    }

    @GetMapping("/employees")
    public String listOfEmployees(Model theModel){
        theModel.addAttribute("employees",employeeList);
        return "Employees";
    }
}

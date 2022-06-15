package dev.spring.p05datarepositoryforspringboot.controller;


import dev.spring.p05datarepositoryforspringboot.entity.Employee;
import dev.spring.p05datarepositoryforspringboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> findAll(){
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable int id){
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.update(employee);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployeeById(@PathVariable int id){
        employeeService.deleteById(id);
        return "Deleted...";
    }

    @GetMapping("/getNumberOfEmployees")
    public String getNumberOfEmployees(){
        int empNumber = employeeService.getNumberOfEmployees();
        return "Total employee number on DB : " + empNumber ;
    }

    @GetMapping("/getAgesWithGrouping")
    public List<?> getAgesWithGrouping(){
        return employeeService.getAgesWithGrouping();
    }

    @GetMapping("/getAgesWithGroupingWithNativeQuery")
    public List<?> getAgesWithGroupingWithNativeQuery(){
        return employeeService.getAgesWithGroupingWithNativeQuery();
    }

    @GetMapping("/findByName/{name}")
    public List<Employee> getEmployeesWithName(@PathVariable String name){
        return employeeService.getEmployeesWithName(name);
    }

    @GetMapping("/findByNameContaining/{name}")
    public List<Employee> getEmployeesWithNameContaining(@PathVariable String name){
        return employeeService.getEmployeesWithNameContaining(name);
    }

    @GetMapping("/findByAgeGreaterThan/{age}")
    public List<Employee> findByAgeGreaterThan(@PathVariable int age){
        return employeeService.findByAgeGreaterThan(age);
    }

    @GetMapping("/findByAgeGreaterThanAndSalaryBefore/{age}/{salary}")
    public List<Employee> findByAgeGreaterThanAndSalaryBefore(@PathVariable int age, @PathVariable double salary){
        return employeeService.findByAgeGreaterThanAndSalaryBefore(age, salary);
    }

    @GetMapping("/findFirst3BySalaryGreaterThan/{salary}")
    public List<Employee> findFirst3BySalaryGreaterThan(@PathVariable double salary){
        return employeeService.findFirst3BySalaryGreaterThan(salary);
    }

    @GetMapping("/deleteByFullName/{name}")
    public String findFirst3BySalaryGreaterThan(@PathVariable String name){
        employeeService.deleteByFullName(name);
        return "Deleted...";
    }
}

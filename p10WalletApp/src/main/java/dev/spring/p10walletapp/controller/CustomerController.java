package dev.spring.p10walletapp.controller;

import dev.spring.p10walletapp.dto.CustomerDto;
import dev.spring.p10walletapp.model.Customer;
import dev.spring.p10walletapp.service.CustomerService;
import dev.spring.p10walletapp.util.ClientRequestInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
//@Slf4j
public class CustomerController {

    private final CustomerService customerService;
    private final ClientRequestInfo clientRequestInfo;

    @PostMapping("/save-customer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody @Valid CustomerDto customerdto){
        //log.info(String.valueOf(clientRequestInfo));
        Optional<Customer> resultOptional = customerService.saveCustomer(customerdto);
        if(resultOptional.isPresent()){
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

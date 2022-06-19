package dev.spring.p10walletapp.service;

import dev.spring.p10walletapp.dto.CustomerDto;
import dev.spring.p10walletapp.exceptions.BadRequestException;
import dev.spring.p10walletapp.mappers.CustomerMapper;
import dev.spring.p10walletapp.model.Customer;
import dev.spring.p10walletapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    public Optional<Customer> saveCustomer(CustomerDto customerDto){

        boolean isExists = customerRepository.selectExistsSsid(customerDto.getSsid());

        if(isExists){
            throw new BadRequestException("Customer with SSID : " + customerDto.getSsid() + " is already exists!");
        }

        /*
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setSsid(customerDto.getSsid());
        customer.setEmail(customerDto.getEmail());
         */

        Customer customer = customerMapper.mapFromCustomerDtoToCustomer(customerDto);

        return Optional.of(customerRepository.save(customer));
    }

}

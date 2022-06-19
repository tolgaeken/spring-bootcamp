package dev.spring.p10walletapp.service;

import dev.spring.p10walletapp.dto.CustomerDto;
import dev.spring.p10walletapp.exceptions.BadRequestException;
import dev.spring.p10walletapp.mappers.CustomerMapper;
import dev.spring.p10walletapp.model.Customer;
import dev.spring.p10walletapp.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    CustomerRepository mockCustomerRepository;

    @Mock
    CustomerMapper mockCustomerMapper;

    @InjectMocks
    CustomerService customerService;


    @Test
    void saveCustomer() {
        // given
        Customer customer = new Customer();
        when(mockCustomerRepository.selectExistsSsid(anyLong())).thenReturn(Boolean.FALSE);
        when(mockCustomerMapper.mapFromCustomerDtoToCustomer(any())).thenReturn(customer);
        when(mockCustomerRepository.save(any())).thenReturn(customer);

        // when
        CustomerDto dto = new CustomerDto();
        Customer actual = this.customerService.saveCustomer(dto).get();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(customer, actual),
                () -> assertEquals(customer.getSsid(), actual.getSsid())
        );
    }

    @Test
    void saveCustomer2() {
        // given
        Customer customer = new Customer();
        when(mockCustomerRepository.selectExistsSsid(anyLong())).thenReturn(Boolean.TRUE);

        // when
        CustomerDto dto = new CustomerDto();
        Executable executable =  () -> this.customerService.saveCustomer(dto).get();

        // then
        assertThrows(BadRequestException.class, executable);
    }
}
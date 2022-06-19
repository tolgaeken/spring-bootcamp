package dev.spring.p10walletapp.mappers;

import dev.spring.p10walletapp.dto.CustomerDto;
import dev.spring.p10walletapp.model.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer mapFromCustomerDtoToCustomer(CustomerDto dto);
    CustomerDto mapFromCustomerToCustomerDto(Customer customer);

}

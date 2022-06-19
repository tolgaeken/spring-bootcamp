package dev.spring.p10walletapp.mappers;

import dev.spring.p10walletapp.dto.WalletDto;
import dev.spring.p10walletapp.model.Wallet;
import dev.spring.p10walletapp.service.WalletService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class WalletMapper {

    @Autowired
    protected WalletService walletService;

    @Mapping(target = "customer", expression = "java(walletService.findCustomerById(walletDto.getCustomerId()))")
    @Mapping(target = "createDate", expression = "java(java.time.LocalDate.now())")
    public abstract Wallet mapFromWalletDtoToWallet(WalletDto walletDto);
    public abstract WalletDto mapFromWallettoWalletDto(Wallet wallet);

}

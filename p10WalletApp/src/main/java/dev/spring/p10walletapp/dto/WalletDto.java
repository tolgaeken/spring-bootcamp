package dev.spring.p10walletapp.dto;

import dev.spring.p10walletapp.model.AbstractBaseEntity;
import dev.spring.p10walletapp.model.enumeration.Currency;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletDto extends AbstractBaseEntity {

    @ApiModelProperty(hidden = true)
    private long id;

    @ApiModelProperty(example = "0.0")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private double balance;

    @ApiModelProperty(example = "TRY or USD or EUR or GBP")
    @NotNull(message = "Currency is mandatory")
    private Currency currency;

    @NotNull(message = "Customer id is mandatory")
    private int customerId;


}

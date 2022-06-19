package dev.spring.p10walletapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.spring.p10walletapp.model.enumeration.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wallet")
public class Wallet extends AbstractBaseEntity {

    private double balance;

    @Enumerated(EnumType.STRING)
    private Currency currency;
    private LocalDate createDate;

    @JsonBackReference
    @ManyToOne
    Customer customer;

}

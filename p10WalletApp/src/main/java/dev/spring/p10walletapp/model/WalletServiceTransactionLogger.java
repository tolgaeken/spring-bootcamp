package dev.spring.p10walletapp.model;

import dev.spring.p10walletapp.model.enumeration.Currency;
import dev.spring.p10walletapp.model.enumeration.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WalletServiceTransactionLogger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private double balanceBefore;
    private double balanceAfter;
    private double transactionAmount;
    @Enumerated(EnumType.STRING)
    private Currency transactionCurrency;
    private LocalDate transactionDateTime;
    private String clientIpAdress;
    private String clientUrl;
    private String sessionActivityId;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}

package dev.spring.p10walletapp.service;

import dev.spring.p10walletapp.dto.WalletDto;
import dev.spring.p10walletapp.exceptions.BadRequestException;
import dev.spring.p10walletapp.exceptions.CustomerNotFoundException;
import dev.spring.p10walletapp.exceptions.NoEnoughBalanceForWithdrawException;
import dev.spring.p10walletapp.exceptions.WalletAlreadyExistsException;
import dev.spring.p10walletapp.mappers.WalletMapper;
import dev.spring.p10walletapp.model.Customer;
import dev.spring.p10walletapp.model.Wallet;
import dev.spring.p10walletapp.model.WalletServiceTransactionLogger;
import dev.spring.p10walletapp.model.enumeration.Currency;
import dev.spring.p10walletapp.model.enumeration.TransactionType;
import dev.spring.p10walletapp.repository.CustomerRepository;
import dev.spring.p10walletapp.repository.TransactionLoggerRepository;
import dev.spring.p10walletapp.repository.WalletRepository;
import dev.spring.p10walletapp.util.ClientRequestInfo;
import dev.spring.p10walletapp.util.ErrorMessageConstants;
import dev.spring.p10walletapp.util.WalletValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private WalletMapper walletMapper;
    @Autowired
    private ClientRequestInfo clientRequestInfo;
    @Autowired
    private TransactionLoggerRepository transactionLoggerRepository;


    @Transactional
    public Optional<Wallet> save_wallet(WalletDto walletDto) {
        this.validateRequest(walletDto);
        Wallet wallet = walletMapper.mapFromWalletDtoToWallet(walletDto);
        if(walletRepository.selectExistsWalletWithSameCurrency(wallet.getCurrency(), wallet.getCustomer().getId())){
            throw new WalletAlreadyExistsException("Wallet with currency type : " + wallet.getCurrency().getCurrencyName()
            + " is already exists!");
        }
        return Optional.of(walletRepository.save(wallet));
    }

    private void validateRequest(WalletDto walletDto) {
        WalletValidatorUtil.validateWalletBalance(walletDto.getBalance());
    }

    public Customer findCustomerById(long customerId){
        Customer foundCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with ID: %d could not foud!", customerId)));
        return foundCustomer;
    }

    @Transactional
    public Optional<Wallet> deposit(long customerId, String currencyName, double amount) {
        Optional<Wallet> wallet = getWallet(customerId, currencyName);
        wallet.get().setBalance(wallet.get().getBalance() + amount);
        walletRepository.save(wallet.get());
        this.saveTransactionToDatabase(wallet.get(), amount, TransactionType.DEPOSIT);
        return wallet;
    }

    @Transactional
    public Optional<Wallet> withdraw(long customerId, String currencyName, double amount) {
        Optional<Wallet> wallet = getWallet(customerId, currencyName);
        if(amount > wallet.get().getBalance()){
            throw new NoEnoughBalanceForWithdrawException(ErrorMessageConstants.No_ENOUGH_BALANCE + amount + " " +
                    wallet.get().getCurrency().getCurrencySign());
        }
        wallet.get().setBalance(wallet.get().getBalance() - amount);
        walletRepository.save(wallet.get());
        this.saveTransactionToDatabase(wallet.get(), amount, TransactionType.WITHDRAW);
        return wallet;
    }

    private void saveTransactionToDatabase(Wallet wallet, double amount, TransactionType transactionType) {
        WalletServiceTransactionLogger transactionLogger = new WalletServiceTransactionLogger();
        transactionLogger.setCustomerId(wallet.getCustomer().getId());
        if(transactionType.equals(TransactionType.DEPOSIT)){
            transactionLogger.setBalanceBefore(wallet.getBalance() - amount);
        } else {
            transactionLogger.setBalanceBefore(wallet.getBalance() + amount);
        }
        transactionLogger.setBalanceAfter(wallet.getBalance());
        transactionLogger.setTransactionAmount(amount);
        transactionLogger.setTransactionCurrency(wallet.getCurrency());
        transactionLogger.setTransactionDateTime(LocalDate.now());
        transactionLogger.setClientUrl(clientRequestInfo.getClientUrl());
        transactionLogger.setClientIpAdress(clientRequestInfo.getClientIpAdress());
        transactionLogger.setSessionActivityId(clientRequestInfo.getSessionActivityId());
        transactionLogger.setTransactionType(transactionType);
        this.transactionLoggerRepository.save(transactionLogger);
    }

    private Optional<Wallet> getWallet(long customerId, String currencyName) {
        Customer customer = this.findCustomerById(customerId);
        Optional<Wallet> wallet = customer.getWallets().stream().filter(w -> w.getCurrency().equals(Currency.valueOf(currencyName))).findFirst();
        if(!wallet.isPresent()){
            throw new BadRequestException("Customer : " + customer.getFirstName()+ " " + customer.getLastName() + " does not have wallet with "
            + currencyName);
        }
        return wallet;
    }


    public Optional<List<Wallet>> getWallets(int customerId) {
        Customer customer = this.findCustomerById(customerId);
        Optional<List<Wallet>> wallets = Optional.of(customer.getWallets());

        if (wallets.get().isEmpty()) {
            wallets = Optional.of(walletRepository.findAll());
        }

        return wallets;
    }

    public Page<List<WalletServiceTransactionLogger>> getAllTransactionsWithDate(String transactionDate, Integer page, Integer size, Pageable pageable) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        WalletValidatorUtil.validateTransactionDate(transactionDate, formatter);
        LocalDate transactionDateResult = LocalDate.parse(transactionDate, formatter);
        if(page != null && size != null){
            pageable = PageRequest.of(page, size);
        }
        return this.transactionLoggerRepository.findAllTransactionByTransactionDate(transactionDateResult, pageable);
    }
}

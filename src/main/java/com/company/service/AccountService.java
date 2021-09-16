package com.company.service;

import com.company.dto.AccountDTO;
import com.company.dto.converter.AccountDTOConverter;
import com.company.dto.request.CreateAccountRequestDTO;
import com.company.dto.request.MoneyTransferRequest;
import com.company.dto.request.UpdateAccountRequest;
import com.company.model.Account;
import com.company.model.Customer;
import com.company.repository.AccountRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountDTOConverter accountDTOConverter;
    private final CustomerService customerService;

    public AccountService(AccountRepository accountRepository, AccountDTOConverter accountDTOConverter, CustomerService customerService) {
        this.accountRepository = accountRepository;
        this.accountDTOConverter = accountDTOConverter;
        this.customerService = customerService;
    }

    public AccountDTO createAccount(CreateAccountRequestDTO createAccountRequestDTO) {

        Account account = Account.builder()
                .id(createAccountRequestDTO.getId())
                .balance(createAccountRequestDTO.getBalance())
                .currency(createAccountRequestDTO.getCurrency())
                .customerId(createAccountRequestDTO.getCustomerId())
                .city(createAccountRequestDTO.getCity())
                .build();
        return accountDTOConverter.convert(accountRepository.save(account));
    }

    @CacheEvict(value = "accounts", allEntries = true)
    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }


    public List<AccountDTO> getAllAccountsDto() {
        List<Account> accountList = accountRepository.findAll();

        return accountList.stream().map(accountDTOConverter::convert).collect(Collectors.toList());
    }

    public AccountDTO getAccountById(String id) {
        return accountRepository.findById(id)
                .map(accountDTOConverter::convert)
                .orElse(new AccountDTO());
    }

    public AccountDTO updateAccount(String id, UpdateAccountRequest request) {
        Customer customer = customerService.getCustomerById(request.getCustomerId());
        if (customer.getId().equals("") ||customer.getId() == null) {
            return AccountDTO.builder().build();
        }

        Optional<Account> accountOptional = accountRepository.findById(id);
        accountOptional.ifPresent(account -> {
            account.setBalance(request.getBalance());
            account.setCity(request.getCity());
            account.setCurrency(request.getCurrency());
            account.setCustomerId(request.getCustomerId());
            accountRepository.save(account);
        });

        return accountOptional.map(accountDTOConverter::convert).orElse(new AccountDTO());
    }
}

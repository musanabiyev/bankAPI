package com.company.dto.converter;

import com.company.dto.AccountDTO;
import com.company.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDTOConverter {

    public AccountDTO convert(Account account){
        return AccountDTO.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .customerId(account.getCustomerId())
                .build();
    }
}

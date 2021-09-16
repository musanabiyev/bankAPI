package com.company.dto;

import com.company.model.City;
import com.company.model.Currency;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccountDTO {

    private String id;

    private String customerId;
    private BigDecimal balance;
    private City city;
    private Currency currency;
}

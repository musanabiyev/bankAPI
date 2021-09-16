package com.company.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Account implements Serializable {

    @Id
    private String id;

    private String customerId;
    private BigDecimal balance;
    private City city;
    private Currency currency;
}

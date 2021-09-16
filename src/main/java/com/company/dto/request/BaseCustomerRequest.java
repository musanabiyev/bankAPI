package com.company.dto.request;

import com.company.dto.CityDTO;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseCustomerRequest {

    private String name;
    private Integer dateOfBirth;
    private CityDTO city;
    private String address;
}

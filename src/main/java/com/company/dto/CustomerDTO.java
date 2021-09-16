package com.company.dto;

import com.company.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private String id;
    private String name;
    private Integer dateOfBirth;
    private CityDTO city;
    private Address address;

}

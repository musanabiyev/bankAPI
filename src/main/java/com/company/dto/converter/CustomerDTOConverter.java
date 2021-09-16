package com.company.dto.converter;

import com.company.dto.CityDTO;
import com.company.dto.CustomerDTO;
import com.company.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDTOConverter {

    public CustomerDTO convert(Customer customer){

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(customer.getId());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setCity(CityDTO.valueOf(customer.getCity().name()));
        customerDTO.setName(customer.getName());
        customerDTO.setDateOfBirth(customer.getDateOfBirth());

        return customerDTO;
    }
}

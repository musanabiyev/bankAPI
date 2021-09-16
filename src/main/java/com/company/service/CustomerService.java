package com.company.service;

import com.company.dto.CustomerDTO;
import com.company.dto.converter.CustomerDTOConverter;
import com.company.dto.request.CreateCustomerResquest;
import com.company.dto.request.UpdateCustomerRequest;
import com.company.model.City;
import com.company.model.Customer;
import com.company.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDTOConverter customerDTOConverter;

    public CustomerService(CustomerRepository customerRepository, CustomerDTOConverter customerDTOConverter) {
        this.customerRepository = customerRepository;
        this.customerDTOConverter = customerDTOConverter;
    }

    public CustomerDTO createCustomer(CreateCustomerResquest createCustomerResquestDTO) {

        Customer customer = new Customer();

        customer.setId(createCustomerResquestDTO.getId());
        //customer.setAddress(customerRequest.getAddress());
        customer.setName(createCustomerResquestDTO.getName());
        customer.setDateOfBirth(createCustomerResquestDTO.getDateOfBirth());
        customer.setCity(City.valueOf(createCustomerResquestDTO.getCity().name()));

        customerRepository.save(customer);
        return customerDTOConverter.convert(customer);

    }


    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();

        List<CustomerDTO> customerDtoList = new ArrayList<>();

        for (Customer customer : customerList) {
            customerDtoList.add(customerDTOConverter.convert(customer));
        }

        return customerDtoList;
    }

    @Transactional
    public CustomerDTO getCustomerDtoById(String id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        return customerOptional.map(customerDTOConverter::convert).orElse(new CustomerDTO());
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    public CustomerDTO updateCustomer(String id, UpdateCustomerRequest customerRequest) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        customerOptional.ifPresent(customer -> {
            customer.setName(customerRequest.getName());
            customer.setCity(City.valueOf(customerRequest.getCity().name()));
            customer.setDateOfBirth(customerRequest.getDateOfBirth());
            //customer.setAddress(customerRequest.getAddress());
            customerRepository.save(customer);
        });

        return customerOptional.map(customerDTOConverter::convert).orElse(new CustomerDTO());
    }


    protected Customer getCustomerById(String id) {
        return customerRepository.findById(id).orElse(new Customer());
    }
}

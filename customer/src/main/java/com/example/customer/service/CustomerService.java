package com.example.customer.service;

import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerByName(String name) {
        return customerRepository.findByName(name);
    }

    public Customer addCustomer(String name) {
        return customerRepository.save(new Customer(name));
    }

    public boolean deleteCustomerByName(String name) {
        Optional<Customer> customerOpt = customerRepository.findByName(name);
        if (customerOpt.isPresent()) {
            customerRepository.delete(customerOpt.get());
            return true;
        }
        return false;
    }

    public Optional<Customer> updateCustomerByName(String name, String newName) {
        Optional<Customer> customerOpt = customerRepository.findByName(name);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            customer.setName(newName);
            return Optional.of(customerRepository.save(customer));
        }
        return Optional.empty();
    }
}
package com.example.customer.controller;

import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping
    public Customer addCustomer(@RequestParam String name) {
        return customerService.addCustomer(name);
    }

    @GetMapping("/{name}")
    public Optional<Customer> getCustomerByName(@PathVariable String name) {
        return customerService.getCustomerByName(name);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteCustomerByName(@PathVariable String name) {
        boolean deleted = customerService.deleteCustomerByName(name);
        if (deleted) {
            return ResponseEntity.ok("Customer '" + name + "' deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer with name '" + name + "' not found.");
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> updateCustomerByName(
            @PathVariable String name,
            @RequestParam String newName) {

        Optional<Customer> updatedCustomer = customerService.updateCustomerByName(name, newName);

        if (updatedCustomer.isPresent()) {
            return ResponseEntity.ok(updatedCustomer.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer with name '" + name + "' not found.");
        }
    }
}

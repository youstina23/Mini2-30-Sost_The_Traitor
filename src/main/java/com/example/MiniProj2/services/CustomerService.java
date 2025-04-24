package com.example.MiniProj2.services;

import com.example.MiniProj2.models.Customer;
import com.example.MiniProj2.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + id));
    }


    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer existingCustomer = getCustomerById(id);
        existingCustomer.setName(customerDetails.getName());
        existingCustomer.setEmail(customerDetails.getEmail());
        existingCustomer.setPhoneNumber(customerDetails.getPhoneNumber());
        return customerRepository.save(existingCustomer);
    }


    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id); // No need to check exists again
    }

    public boolean existsById(Long id) {
        return customerRepository.existsById(id);
    }


    public List<Customer> findCustomersByEmailDomain(String domain) {
        return customerRepository.findByEmailDomain(domain);
    }


    public List<Customer> findCustomersByPhonePrefix(String prefix) {
        return customerRepository.findByPhonePrefix(prefix);
    }
}

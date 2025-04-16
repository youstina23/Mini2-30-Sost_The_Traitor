package com.example.MiniProj2.repositories;

import com.example.MiniProj2.models.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// Make sure this is an interface, not a class
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Find customers by email domain (e.g., ends with "@gmail.com")
    @Modifying
    @Transactional
    @Query("SELECT c FROM Customer c WHERE c.email LIKE %:domain")
    List<Customer> findByEmailDomain(@Param("domain") String domain);

    // Find customers by phone number prefix (e.g., starts with "+20")
    @Modifying
    @Transactional
    @Query("SELECT c FROM Customer c WHERE c.phoneNumber LIKE :prefix%")
    List<Customer> findByPhonePrefix(@Param("prefix") String prefix);
}


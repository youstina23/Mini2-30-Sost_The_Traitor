package com.example.MiniProj2.repositories;



import com.example.MiniProj2.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByTripId(Long tripId);

    List<Payment> findByAmountGreaterThan(Double threshold);
}

package com.example.MiniProj2.services;



import com.example.MiniProj2.models.Payment;
import com.example.MiniProj2.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // 1. Add Payment
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // 2. Get All Payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // 3. Get Payment By ID
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Payment with ID " + id + " not found"));
    }

    // 4. Update Payment
    public Payment updatePayment(Long id, Payment payment) {
        Payment existingPayment = getPaymentById(id);
        existingPayment.setAmount(payment.getAmount());
        existingPayment.setPaymentMethod(payment.getPaymentMethod());
        existingPayment.setPaymentStatus(payment.getPaymentStatus());
        existingPayment.setTrip(payment.getTrip());
        return paymentRepository.save(existingPayment);
    }

    // 5. Delete Payment
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new NoSuchElementException("Payment with ID " + id + " not found");
        }
        paymentRepository.deleteById(id);
    }

    // 6. Find Payments By Trip ID
    public List<Payment> findPaymentsByTripId(Long tripId) {
        return paymentRepository.findByTripId(tripId);
    }

    // 7. Find Payments With Amount Greater Than a Threshold
    public List<Payment> findByAmountThreshold(Double threshold) {
        return paymentRepository.findByAmountGreaterThan(threshold);
    }
}

package com.project.Perseo_Academy.services;

import com.project.Perseo_Academy.models.Payment;
import com.project.Perseo_Academy.repositories.IPaymentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final IPaymentRepository iPaymentRepository;
    public PaymentService(IPaymentRepository iPaymentRepository) {
        this.iPaymentRepository = iPaymentRepository;
    }

    public List<Payment> getAllPayments() {
        return iPaymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return iPaymentRepository.findById(id);
    }

    public Payment createPayment(Payment payment) {
        return iPaymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        iPaymentRepository.deleteById(id);
    }


}

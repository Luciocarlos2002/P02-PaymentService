package com.microservice.service;

import com.microservice.model.Payment;
import com.microservice.repository.PaymentRepository;
import com.microservice.service.impl.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentServiceImpl {

    private final PaymentRepository paymentRepository;

    @Override
    public Flux<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    @Override
    public Mono<Payment> getByIdPayment(String id) {
        return paymentRepository.findById(id);
    }

    @Override
    public Mono<Payment> createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Mono<Payment> updatePayment(String id, Payment payment) {

        return paymentRepository.findById(id).flatMap(payment1 ->{
            payment1.setAmount(payment.getAmount());
            payment1.setDateLimit(payment.getDateLimit());
            payment1.setCommission(payment.getCommission());
            payment1.setDescription(payment.getDescription());
            return paymentRepository.save(payment1);
        }).switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Void> deletePayment(String id) {
        return paymentRepository.findById(id).flatMap(payment -> paymentRepository.deleteById(payment.getId()));
    }
}

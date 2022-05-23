package com.microservice.service.impl;

import com.microservice.model.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentServiceImpl {

    Flux<Payment> getAllPayment();

    Mono<Payment> getByIdPayment(String id);

    Mono<Payment> createPayment(Payment payment);

    Mono<Payment> updatePayment(String id, Payment payment);

    Mono<Void> deletePayment(String id);
}

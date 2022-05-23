package com.microservice.controller;

import com.microservice.model.Payment;
import com.microservice.service.impl.PaymentServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    private static final String PAYMENTSERVICE = "paymentservice";

    @GetMapping(value = "/allPayments")
    public Flux<Payment> getAllPayments(){
        return paymentService.getAllPayment();
    }

    @GetMapping(value = "/{id}")
    public Mono<Payment> getByIdPayment(@PathVariable String id){
        return paymentService.getByIdPayment(id);
    }

    @PostMapping(value = "/create")
    @CircuitBreaker(name = PAYMENTSERVICE, fallbackMethod = "paymentservice")
    public Mono<Payment> createPayment(@RequestBody Payment payment){
        return paymentService.createPayment(payment);
    }

    @PutMapping(value = "/update/{id}")
    @CircuitBreaker(name = PAYMENTSERVICE, fallbackMethod = "paymentservice")
    public Mono<Payment> updatePayment(@PathVariable String id, @RequestBody Payment payment){
        return paymentService.updatePayment(id, payment);
    }

    @DeleteMapping(value = "/delete/{id}")
    public Mono<Void> deletePayment(@PathVariable String id){
        return paymentService.deletePayment(id);
    }

}

package com.payments.PaymentsAPI.service;

import com.payments.PaymentsAPI.entity.Payment;

import java.util.concurrent.ExecutionException;

public interface PaymentService {
    void sendPaymentEvent(Payment payment) throws ExecutionException, InterruptedException;
}
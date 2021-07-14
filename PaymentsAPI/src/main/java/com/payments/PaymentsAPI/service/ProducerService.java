package com.payments.PaymentsAPI.service;

import com.payments.PaymentsAPI.entity.Payment;

import java.util.concurrent.ExecutionException;

public interface ProducerService {
    void sendMessage(Payment message) throws ExecutionException, InterruptedException;
}

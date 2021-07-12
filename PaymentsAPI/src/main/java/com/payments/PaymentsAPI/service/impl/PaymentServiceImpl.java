package com.payments.PaymentsAPI.service.impl;

import com.payments.PaymentsAPI.entity.Payment;
import com.payments.PaymentsAPI.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payments.PaymentsAPI.service.ProducerService;
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private ProducerService producerService;

    @Override
    public void sendPaymentEvent(Payment payment) {
        producerService.sendMessage(payment.);
    }
}
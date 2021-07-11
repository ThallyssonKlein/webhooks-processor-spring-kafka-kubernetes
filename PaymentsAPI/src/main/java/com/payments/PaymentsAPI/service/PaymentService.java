package com.payments.PaymentsAPI.service;

import com.payments.PaymentsAPI.entity.Payment;

public interface PaymentService {
    void sendPaymentEvent(Payment payment);
}
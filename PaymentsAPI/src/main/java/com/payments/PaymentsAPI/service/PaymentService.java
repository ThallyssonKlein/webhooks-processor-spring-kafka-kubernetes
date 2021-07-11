package com.payments.PaymentsAPI.service;

import com.payments.PaymentsAPI.Payment;

public interface PaymentService {
    void sendPaymentEvent(Payment payment);
}
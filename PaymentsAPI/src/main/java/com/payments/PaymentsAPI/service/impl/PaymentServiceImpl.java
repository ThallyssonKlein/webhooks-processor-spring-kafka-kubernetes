package com.payments.PaymentsAPI.service.impl;

import com.payments.PaymentsAPI.entity.Payment;
import com.payments.PaymentsAPI.service.PaymentService;

public class PaymentServiceImpl implements PaymentService {

    @Override
    public void sendPaymentEvent(Payment payment) {
        System.out.print(payment);
    }
}
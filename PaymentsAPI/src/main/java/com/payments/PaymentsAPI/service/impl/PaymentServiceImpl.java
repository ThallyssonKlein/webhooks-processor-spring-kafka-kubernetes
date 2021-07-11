package com.payments.PaymentsAPI.service.impl;

import com.payments.PaymentsAPI.Payment;
import com.payments.PaymentsAPI.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public void sendPaymentEvent(Payment payment) {
        System.out.print(payment);
    }
}
package com.payments.PaymentsAPI.service;

import com.payments.PaymentsAPI.entity.Payment;
public interface ProducerService {
    void sendMessage(Payment message);
}

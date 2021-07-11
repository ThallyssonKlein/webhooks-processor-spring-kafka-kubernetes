package com.payments.PaymentsAPI.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.payments.PaymentsAPI.entity.Payment;
import com.payments.PaymentsAPI.service.PaymentService;

@RestController
@RequestMapping(value = "/api")
@Api(value = "Recurso de pagamento")
@CrossOrigin(origins = "*")
public class PaymentResource {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/v1/payment")
    @ApiOperation(value = "Cria um novo pagamento")
    public Payment postPayment(@Valid @RequestBody Payment payment){
        paymentService.sendPaymentEvent(payment);
        return payment;
    }
}
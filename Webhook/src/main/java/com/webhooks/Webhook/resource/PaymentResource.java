package com.webhooks.Webhook.resource;

import com.webhooks.Webhook.entity.Payment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
@Api(value = "Recurso de pagamento")
@CrossOrigin(origins = "*")
public class PaymentResource {

    @PostMapping("/v1/payment")
    @ApiOperation(value = "Recebe um pagamento")
    public Payment postPayment(@Valid @RequestBody Payment payment) {
        System.out.println(payment);
        return payment;
    }
}
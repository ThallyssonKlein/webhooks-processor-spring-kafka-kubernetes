package com.payments.PaymentsAPI.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment implements Serializable {
    
    private enum Flag{
        MASTERCARD,
        VISA,
        AMERICAN_EXPRESS,
        HIPERCARD,
        ELO
    }

    @NotNull
    @NotEmpty
    private String person;
    @NotNull
    private Double value;
    @NotNull
    private Flag flag;
}
package com.payments.PaymentsAPI;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    private String personName;
    private Double value;
    private Flag flag; 
}
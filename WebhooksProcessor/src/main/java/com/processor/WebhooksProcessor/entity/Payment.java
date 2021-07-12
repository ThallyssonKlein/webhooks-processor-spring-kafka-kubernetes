package com.processor.WebhooksProcessor.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String person;
    private Double value;
    private Flag flag;
}
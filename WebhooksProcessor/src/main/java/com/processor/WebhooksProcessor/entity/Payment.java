package com.processor.WebhooksProcessor.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Payment implements Serializable {
    
    public enum Flag{
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
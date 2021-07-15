package com.webhooks.Webhook.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @NotEmpty
    private String person;
    @NotNull
    private Double value;
    @NotNull
    private Flag flag;
}
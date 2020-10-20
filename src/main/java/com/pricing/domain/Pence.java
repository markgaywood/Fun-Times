package com.pricing.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Pence {
    public static Pence pence(Integer value) { return Pence.builder().value(value).build(); }

    Integer value;

    public Pence multiplyBy(Quantity quantity) {
        return Pence.builder()
                .value(value * quantity.getAmount())
                .build();
    }
}

package com.pricing.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Pence {
    public static Pence pence(Integer value) { return Pence.builder().value(value).build(); }

    public static Pence pence(Integer pounds, Integer pence) { return pence(pounds * 100 + pence); }

    Integer value;

    public Pence add(Pence that) { return pence(this.value + that.value); }

    public Pence multiplyBy(Quantity quantity) {
        return pence(value * quantity.getAmount());
    }
}

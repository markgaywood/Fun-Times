package com.pricing.promotions;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class PercentDiscount extends Discount {
    public static PercentDiscount percentDiscount(Double value) {
        return new PercentDiscount(new BigDecimal(value / 100.0));
    }

    BigDecimal percentage;

    private PercentDiscount(BigDecimal value) {
        percentage = value;
    }
}

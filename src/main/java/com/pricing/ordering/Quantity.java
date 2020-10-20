package com.pricing.ordering;

import lombok.Value;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Value
public class Quantity {
    public static final Optional<Quantity> quantity(Integer value) {
        if (value < 0) { return empty(); }

        return of(new Quantity(value));
    }

    Integer amount;

    private Quantity(Integer value) {
        amount = value;
    }
}

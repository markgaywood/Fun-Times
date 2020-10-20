package com.pricing.ordering;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.pricing.ordering.Quantity.quantity;
import static org.assertj.core.api.Assertions.assertThat;

public class QuantityTests {
    @Test
    public void givenAPositiveValue_whenQuantityConstructed_thenAValidQuantityIsBuilt() {
        Optional<Quantity> actual = quantity(12);

        assertThat(actual.get().getAmount()).isEqualTo(12);
    }

    @Test
    public void givenAZeroValue_whenQuantityConstructed_thenAValidQuantityIsBuilt() {
        Optional<Quantity> actual = quantity(0);

        assertThat(actual.get().getAmount()).isEqualTo(0);
    }

    @Test
    public void givenANegativeValue_whenQuantityConstructed_thenQuantityIsNotBuilt() {
        Optional<Quantity> actual = quantity(-1);

        assertThat(actual.isPresent()).isFalse();
    }
}

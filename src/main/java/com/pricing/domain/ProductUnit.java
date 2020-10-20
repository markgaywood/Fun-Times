package com.pricing.domain;

import lombok.Getter;

public enum ProductUnit {
    Tin("tin of "), Loaf("loaf of "), Bottle("bottle of "), Single("one ");

    @Getter
    private final String label;

    ProductUnit(String value) {
        this.label = value;
    }
}

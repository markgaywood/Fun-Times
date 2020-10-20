package com.pricing.stock;

import java.util.Optional;

public interface StockItems {
    Optional<StockItem> findByName(String value);
}

package com.pricing.promotions;

import com.pricing.ordering.Quantity;
import com.pricing.product.Product;
import lombok.Value;

import static com.pricing.promotions.PercentDiscount.percentDiscount;

public class LinkedItemsDiscount extends Discount {

    @Value
    public static class Buy {
        Product product;
        Quantity quantity;
    }

    @Value
    public static class Get {
        Product product;
        PercentDiscount discount;

        private Get(Product product, Double value) {
            this.product = product;
            discount = percentDiscount(value);
        }
    }
}

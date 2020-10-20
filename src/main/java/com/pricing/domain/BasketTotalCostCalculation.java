package com.pricing.domain;

import static com.pricing.domain.Pence.pence;

@FunctionalInterface
public interface BasketTotalCostCalculation {
    Pence calculateTotalCost(Basket basket);

    class BasketTotalCostCalculator implements BasketTotalCostCalculation {
        public static Pence calculateBasketTotalCost(Basket basket) {
            BasketTotalCostCalculation calculator = new BasketTotalCostCalculator();
            return calculator.calculateTotalCost(basket);
        }

        private BasketTotalCostCalculator() {}

        @Override
        public Pence calculateTotalCost(Basket basket) {
            return basket.getOrderedItems().stream().map(OrderedItem::getCost).reduce(Pence::add)
                    .orElse(pence(0));
        }
    }
}

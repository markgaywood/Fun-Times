package com.ford.exercise.shoppingcart.dicount;

import com.ford.exercise.shoppingcart.cart.ShoppingCartItem;
import org.javamoney.moneta.Money;

import java.time.LocalDate;

public class BuyXQuantityGetYPercentOffDiscountCalculator extends Discount {

    /**
     *
     * @param conditionItem
     * @param rewardItem
     * @return
     */
    public Money calculate(ShoppingCartItem conditionItem, ShoppingCartItem rewardItem) {

        Money result = Money.of(0, rewardItem.getCost().getCurrency());

        if(conditionItem.getQuantity() >= getCondition().getQuantity()) {
            int t = Math.floorDiv(conditionItem.getQuantity(), getCondition().getQuantity());
            return rewardItem.getCost().multiply(getReward().getPercent()).multiply(t);
        }

        return result;
    }




}

package com.ford.exercise.shoppingcart.dicount;

import com.ford.exercise.shoppingcart.cart.ShoppingCartItem;
import org.javamoney.moneta.Money;

interface DiscountCalculator {

    public Money calculate(ShoppingCartItem conditionItem, ShoppingCartItem rewardItem);


}

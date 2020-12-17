package com.ford.exercise.shoppingcart.dicount;

import com.ford.exercise.shoppingcart.cart.ShoppingCartItem;
import com.ford.exercise.shoppingcart.inventory.InventoryRepository;
import com.ford.exercise.shoppingcart.inventory.StockItem;
import com.ford.exercise.shoppingcart.TestFactory;
import org.javamoney.moneta.Money;
import org.junit.Test;

public class BuyXQuantityGetYPercentOffDiscountCalculatorTest {

    @Test
    public void calculate() {

        InventoryRepository inventoryRepository = TestFactory.createInventoryRepository();

        StockItem stockItem = inventoryRepository.get("milk");
        ShoppingCartItem item = new ShoppingCartItem();
        item.setQuantity(3);
        item.setCost(Money.of(stockItem.getCost(), "GBP"));

        // For every 2, get one half price
        BuyXQuantityGetYPercentOffDiscountCalculator discount = new BuyXQuantityGetYPercentOffDiscountCalculator();

        // Buy 2 tins of soup and get a loaf of bread half price
        DiscountCondition condition = new DiscountCondition();
        condition.setProduct("soup");
        condition.setQuantity(2);

        DiscountReward reward = new DiscountReward();
        reward.setProduct("bread");
        reward.setPercent(0.5);




        // Apples have a 10% discount
        DiscountCondition conditionApples = new DiscountCondition();
        condition.setProduct("apples");
        condition.setQuantity(1);

        DiscountReward rewardApples = new DiscountReward();
        reward.setProduct("apples");
        reward.setPercent(0.1);


    }
}
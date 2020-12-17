package com.ford.exercise.shoppingcart;

import com.ford.exercise.shoppingcart.dicount.BuyXQuantityGetYPercentOffDiscountCalculator;
import com.ford.exercise.shoppingcart.dicount.DiscountCondition;
import com.ford.exercise.shoppingcart.dicount.DiscountRepository;
import com.ford.exercise.shoppingcart.dicount.DiscountReward;
import com.ford.exercise.shoppingcart.inventory.InventoryRepository;
import com.ford.exercise.shoppingcart.inventory.StockItem;

import java.time.LocalDate;
import java.time.Period;

import static java.time.temporal.TemporalAdjusters.*;

public class TestFactory {

    public static InventoryRepository createInventoryRepository() {

        InventoryRepository inventory = new InventoryRepository();
        inventory.add(new StockItem("soup", "unit", 0.65));
        inventory.add(new StockItem("bread", "loaf", 0.80));
        inventory.add(new StockItem("milk", "bottle", 1.30));
        inventory.add(new StockItem("apples", "single", 0.10));

        return inventory;
    }

    public static DiscountRepository createDiscountRepository() {

        DiscountRepository discounts = new DiscountRepository();

        BuyXQuantityGetYPercentOffDiscountCalculator buy2TinsOfSoupGetALoafHalfPrice = new BuyXQuantityGetYPercentOffDiscountCalculator();

        buy2TinsOfSoupGetALoafHalfPrice.setCondition(new DiscountCondition("soup", 2));
        buy2TinsOfSoupGetALoafHalfPrice.setReward(new DiscountReward("bread", 1, 0.5));
        buy2TinsOfSoupGetALoafHalfPrice.setValidFrom(LocalDate.now().minus(Period.ofDays(1))); // Started yesterday
        buy2TinsOfSoupGetALoafHalfPrice.setValidTo(LocalDate.now().plus(Period.ofDays(6))); // Ends in 6 days, 7 days from start date


        discounts.add(buy2TinsOfSoupGetALoafHalfPrice);

        BuyXQuantityGetYPercentOffDiscountCalculator apples10PercentOff = new BuyXQuantityGetYPercentOffDiscountCalculator();
        apples10PercentOff.setCondition(new DiscountCondition("apples", 1));
        apples10PercentOff.setReward(new DiscountReward("apples", 1, 0.1));
        apples10PercentOff.setValidFrom(LocalDate.now().plus(Period.ofDays(3))); // Starts in 3 days
        apples10PercentOff.setValidTo(LocalDate.now().plus(Period.ofMonths(1)).with(lastDayOfMonth())); // Until end of next month


        discounts.add(apples10PercentOff);

        return discounts;
    }




}

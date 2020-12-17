package com.ford.exercise.shoppingcart.cart;

import com.ford.exercise.shoppingcart.dicount.BuyXQuantityGetYPercentOffDiscountCalculator;
import com.ford.exercise.shoppingcart.dicount.DiscountCondition;
import com.ford.exercise.shoppingcart.dicount.DiscountRepository;
import com.ford.exercise.shoppingcart.dicount.DiscountReward;
import com.ford.exercise.shoppingcart.inventory.InventoryRepository;
import com.ford.exercise.shoppingcart.TestFactory;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import static org.junit.Assert.*;

public class ShoppingCartTest {

    private InventoryRepository inventoryRepository;

    private DiscountRepository discountRepository;

    @Before
    public void setUp() throws Exception {
        inventoryRepository = TestFactory.createInventoryRepository();
        discountRepository = TestFactory.createDiscountRepository();
    }



    /**
     *
     */
    @Test
    public void basketContaining3TinsOFSoupAnd2LoaafsOfBreadBoughtToday() throws Exception {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setOrderDate(LocalDate.now());
        shoppingCart.setInventoryRepository(inventoryRepository);
        shoppingCart.setDiscountRepository(discountRepository);

        shoppingCart.add("soup", 3);
        shoppingCart.add("bread", 2);

        assertEquals(BigDecimal.valueOf(3.15), shoppingCart.getNetTotal().getNumberStripped());
    }

    @Test
    public void basketContaining6ApplesAndABottleOfMilkBoughtToday() throws Exception {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setOrderDate(LocalDate.now());
        shoppingCart.setInventoryRepository(inventoryRepository);
        shoppingCart.setDiscountRepository(discountRepository);

        shoppingCart.add("apples", 6);
        shoppingCart.add("milk", 1);

        assertEquals(BigDecimal.valueOf(0), shoppingCart.getDiscountTotal().getNumberStripped());
        assertEquals(BigDecimal.valueOf(1.90), shoppingCart.getNetTotal().getNumberStripped());
    }

    @Test
    public void basketContaining6ApplesAndABottleOfMilkBoughtIn5DaysTime() throws Exception {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setOrderDate(LocalDate.now().plus(Period.ofDays(5)));
        shoppingCart.setInventoryRepository(inventoryRepository);
        shoppingCart.setDiscountRepository(discountRepository);

        shoppingCart.add("apples", 6);
        shoppingCart.add("milk", 1);

        assertEquals(BigDecimal.valueOf(0.06), shoppingCart.getDiscountTotal().getNumberStripped());
        assertEquals(BigDecimal.valueOf(1.84), shoppingCart.getNetTotal().getNumberStripped());
    }

    @Test
    public void basketContaining3Apples2TinsOfSoupAndALoafBreadBoughtIn5DaysTime() throws Exception {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setOrderDate(LocalDate.now().plus(Period.ofDays(5)));
        shoppingCart.setInventoryRepository(inventoryRepository);
        shoppingCart.setDiscountRepository(discountRepository);

        shoppingCart.add("apples", 3);
        shoppingCart.add("soup", 2);
        shoppingCart.add("bread", 1);

        assertEquals(BigDecimal.valueOf(0.43), shoppingCart.getDiscountTotal().getNumberStripped());
        assertEquals(BigDecimal.valueOf(1.97), shoppingCart.getNetTotal().getNumberStripped());
    }

    @Test
    public void getTotalCost() {
    }

    @Test
    public void isApplicable() {
    }

    @Test
    public void isConditionApplicable() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setInventoryRepository(inventoryRepository);
        shoppingCart.add("soup", 3);
        shoppingCart.add("bread", 2);
        assertEquals(true, shoppingCart.isConditionApplicable(new DiscountCondition("soup", 2)));
        assertEquals(false, shoppingCart.isConditionApplicable(new DiscountCondition("soup", 4)));
    }


    @Test
    public void isRewardApplicable() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setInventoryRepository(inventoryRepository);
        shoppingCart.add("soup", 3);
        shoppingCart.add("bread", 2);
        assertEquals(true, shoppingCart.isRewardApplicable(new DiscountReward("soup", 1, 0.1)));
        assertEquals(false, shoppingCart.isRewardApplicable(new DiscountReward("soup", 5, 0.1)));
        assertEquals(true, shoppingCart.isRewardApplicable(new DiscountReward("bread", 1, 0.1)));
        assertEquals(false, shoppingCart.isRewardApplicable(new DiscountReward("bread", 5, 0.1)));
        assertEquals(false, shoppingCart.isRewardApplicable(new DiscountReward("apples", 1, 0.1)));
    }

    @Test
    public void isDiscountApplicable() throws Exception {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setInventoryRepository(inventoryRepository);
        shoppingCart.add("soup", 3);
        shoppingCart.add("bread", 2);

    }

    @Test
    public void getItemDiscount() throws Exception {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setInventoryRepository(inventoryRepository);
        shoppingCart.add("soup", 3);
        shoppingCart.add("bread", 2);

        BuyXQuantityGetYPercentOffDiscountCalculator buy2TinsOfSoupGetALoafHalfPrice = new BuyXQuantityGetYPercentOffDiscountCalculator();

        buy2TinsOfSoupGetALoafHalfPrice.setCondition(new DiscountCondition("soup", 2));
        buy2TinsOfSoupGetALoafHalfPrice.setReward(new DiscountReward("bread", 1, 0.5));
        buy2TinsOfSoupGetALoafHalfPrice.setValidTo(LocalDate.now());
        buy2TinsOfSoupGetALoafHalfPrice.setValidFrom(LocalDate.now());

        Money discounTotal = shoppingCart.getItemDiscount(buy2TinsOfSoupGetALoafHalfPrice, shoppingCart.get("bread"));

        assertEquals(BigDecimal.valueOf(0.40), discounTotal.getNumberStripped());


    }
}
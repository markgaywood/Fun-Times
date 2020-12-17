package com.ford.exercise.shoppingcart;

import com.ford.exercise.shoppingcart.cart.ShoppingCart;
import com.ford.exercise.shoppingcart.cart.ShoppingCartItem;
import com.ford.exercise.shoppingcart.cart.exception.InvalidChartIteException;
import com.ford.exercise.shoppingcart.inventory.StockItem;
import org.javamoney.moneta.Money;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Application {

    public static ShoppingCart shoppingCart;

    public static String lastEntry = "";

    public static String entryInstruction = "Please enter product and quantity separated by comma example :  milk,1";

    public static void main(String[] args) throws Exception {

        Scanner command = new Scanner(System.in);

        startShopping();

        System.out.println("To restart shopping, enter : start");
        System.out.println("To exit application, enter : exit");
        System.out.println("To print shopping total, enter : total");
        System.out.println("To set order date (format : YYYY-MM-dd - 2020-12-17), enter : date");
        System.out.println(entryInstruction + " : ");
        boolean running = true;

        while(running){

            String entry = command.nextLine();

            switch(entry){

                case "start":
                    System.out.println("Shopping started. " + entryInstruction);
                    startShopping();
                    break;
                case "total":
                    Money total = shoppingCart.getNetTotal();
                    System.out.println("Total cost is : " + total);
                    break;

                case "date":
                    System.out.println("Set order date in YYYY-MM-dd format example 2020-12-17 ");
                    break;

                case "exit":
                    System.out.println("Application Closed");
                    running = false;
                    break;

                default:
                    if(lastEntry.equalsIgnoreCase("date")) {
                        setOrderDate(entry);
                    } else {
                        addItem(entry);
                    }
                    break;
            }
            lastEntry = entry;
        }
        command.close();

    }

    public static void startShopping() {

        shoppingCart = new ShoppingCart();
        shoppingCart.setOrderDate(LocalDate.now());
        shoppingCart.setInventoryRepository(Factory.createInventoryRepository());
        shoppingCart.setDiscountRepository(Factory.createDiscountRepository());

    }

    public static void addItem(String productAndQuantity) {
        try {

            String[] split = productAndQuantity.split(",");
            String product = split[0].trim();
            shoppingCart.add(product, Integer.valueOf(split[1].trim()));

            ShoppingCartItem item = shoppingCart.get(product);
            if(item != null) {
                System.out.printf("%s %s items added in cart \n", item.getQuantity(), product);
                Money total = shoppingCart.getNetTotal();
                System.out.println("Total amount is : " + total);
            }

        } catch (InvalidChartIteException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid entry : " + productAndQuantity);
        }
    }

    public static boolean setOrderDate(String date) {
        try {
            shoppingCart.setOrderDate(LocalDate.parse(date));
            System.out.println("Order date set to  " + shoppingCart.getOrderDate());

            return true;
        } catch (Exception e) {
            System.out.println("Failed to change order date. Current order date is " + shoppingCart.getOrderDate());
            System.out.printf(entryInstruction);
        }
        return false;

    }


}

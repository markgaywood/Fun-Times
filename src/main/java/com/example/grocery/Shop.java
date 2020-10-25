package com.example.grocery;

import com.example.grocery.io.InputOutput;
import com.example.grocery.service.BasketService;
import com.example.grocery.service.ProductLookupService;
import com.example.grocery.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.String.format;

@Service
public class Shop {

    private static final String BUY = "BUY";
    private static final String TOTAL = "TOTAL";
    private static final String CLEAR = "CLEAR";
    private static final String QUIT = "QUIT";
    private static final String HELP = "HELP";

    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    private final InputOutput io;
    private final BasketService basketService;
    private final ProductLookupService productLookupService;

    @Autowired
    public Shop(InputOutput io, BasketService basketService, ProductLookupService productLookupService) {
        this.io = io;
        this.basketService = basketService;
        this.productLookupService = productLookupService;
    }

    public void serve() throws IOException {
        help("Welcome to Henry's Grocery");
        for (;;) {
            String command = io.readLine("shop>");
            try (Scanner scanner = new Scanner(command)) {
                switch (scanner.next().toUpperCase()) {

                    case BUY:
                        buy(scanner.nextInt(), scanner.next());
                        break;

                    case TOTAL:
                        total();
                        break;

                    case CLEAR:
                        clear();
                        break;

                    case HELP:
                        help("");
                        break;

                    case QUIT:
                        return;

                    default:
                        throw new IllegalArgumentException(format("I don't understand '%s'", command));
                }

            } catch (IllegalArgumentException ex) {
                help(ex.getMessage());
            } catch (InputMismatchException ex) {
                help("Expected a number");
            } catch (NoSuchElementException ex) {
                help("Expected more input");
            }
        }
    }

    private void buy(int quantity, String name) {
        Product product = productLookupService.lookup(name);
        if (product == null) {
            throw new IllegalArgumentException(format("Unknown product: '%s'", name));
        }
        basketService.addItem(quantity, product);
        io.printf("Added %d %s to basket%n", quantity, product.getName());
    }

    private void total() {
        io.printf("Total purchases: %s%n", decimalFormat.format(basketService.priceUp()));
    }

    private void clear() {
        basketService.clear();
        io.printf("Basket is empty%n");
    }

    private void help(String message) {
        io.printf("%s%nYou can enter these commands:%n", message);
        io.printf("    %s <qty> [%s] - add items to basket%n", BUY, String.join("|", productLookupService.getNames()));
        io.printf("    %s - show the total value of the basket%n", TOTAL);
        io.printf("    %s - empty the basket and start again%n", CLEAR);
        io.printf("    %s - leave the shop%n", QUIT);
        io.printf("    %s - show this message%n", HELP);
    }
}

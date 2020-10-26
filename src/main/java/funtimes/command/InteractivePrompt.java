package funtimes.command;

import funtimes.StockItem;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;

public class InteractivePrompt {

    private static final Map<Integer, StockItem> ITEM_OPTIONS = Collections
            .unmodifiableMap(new TreeMap<Integer, StockItem>() {{
                put(1, StockItem.SOUP);
                put(2, StockItem.BREAD);
                put(3, StockItem.MILK);
                put(4, StockItem.APPLE);
            }});

    public enum Option {
        SYSTEM_OPTION, STOCK_ITEM_OPTION
    }

    public String greeting() {
        return "*** Greetings from Henry's Grocery ***";
    }

    public String optionsLevel1() {
        return "Please enter a number or letter to select an item from below.\n"
               + " [0] - Get a new shopping cart, or a number to add items to today's shopping cart\n"
               + getOptions()
               + " [T] - Get a running total\n"
               + " [Z] - Proceed to check out\n\n";
    }

    public String getOptions() {
        StringBuilder builder = new StringBuilder();
        ITEM_OPTIONS.keySet()
                .forEach(i -> builder.append(" [")
                        .append(i)
                        .append("] - Add [")
                        .append(ITEM_OPTIONS.get(i).getProduct())
                        .append("] to shopping cart\n"));
        return builder.toString();
    }

    public boolean isItemOptionAvailable(String option) {
        if (!NumberUtils.isDigits(option)) {
            return false;
        }
        return isItemOptionAvailable(Integer.valueOf(option));
    }

    public boolean isItemOptionAvailable(Integer option) {
        return ITEM_OPTIONS.containsKey(option);
    }

    public Optional<Option> detectOption(String option) {
        if (Arrays.asList("0", "T", "Z").contains(option)) {
            return Optional.of(Option.SYSTEM_OPTION);
        }
        if (isItemOptionAvailable(option)) {
            return Optional.of(Option.STOCK_ITEM_OPTION);
        }
        return Optional.empty();

    }

    public StockItem numberToStockItem(Integer option) {
        return ITEM_OPTIONS.get(option);
    }

    public String askUnits(Integer option) {
        return ITEM_OPTIONS.get(option).getUnit().getUnit()
               + " of [ "
               + ITEM_OPTIONS.get(option).getProduct()
               + " ] would you like?";
    }

    public String promptHowManyDays() {
        return "How many days in the future would you like your new shopping cart created? Enter 0 for a shopping cart now.";
    }

    public String promptNotRecognized(String option) {
        return "[ " + option + " ] is not recognized";
    }

}

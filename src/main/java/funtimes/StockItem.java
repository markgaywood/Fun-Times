package funtimes;

import java.math.BigDecimal;

public class StockItem {
    public static final StockItem SOUP = new StockItem("soup", Unit.TIN, 0.65);
    public static final StockItem BREAD = new StockItem("bread", Unit.LOAF, 0.80);
    public static final StockItem MILK = new StockItem("milk", Unit.BOTTLE, 1.30);
    public static final StockItem APPLE = new StockItem("apple", Unit.SINGLE, 0.10);

    private String product;
    private Unit unit;
    private BigDecimal cost;

    StockItem(String product, Unit unit, double cost) {
        this.product = product;
        this.unit = unit;
        this.cost = DecimalHelper.bigDecimalFromDoubleValue(cost);
    }

    public String getProduct() {
        return product;
    }

    public Unit getUnit() {
        return unit;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public enum Unit {
        TIN("tin"), LOAF("loaf"), BOTTLE("bottle"), SINGLE("single");

        private String unit;

        Unit(String unit) {
            this.unit = unit;
        }

        public String getUnit() {
            return unit;
        }
    }

}

package funtimes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static funtimes.App.APP_DATE;

public class Discount {

    public static final Discount BUY_2_TINS_OF_SOUP_GET_1_LOAF_OF_BREAD_HALF_PRICE
            = new Discount(StockItem.BREAD,
            0.5, APP_DATE.minusDays(1), APP_DATE.plusDays(7), new Contigent(StockItem.SOUP, 2));

    public static final Discount APPLE_10_PERCENT_DISCOUNT
            = new Discount(StockItem.APPLE,
            0.1, APP_DATE.plusDays(3), APP_DATE.plusMonths(2).minusDays(1));

    public static final List<Discount> OFFERS = Arrays
            .asList(BUY_2_TINS_OF_SOUP_GET_1_LOAF_OF_BREAD_HALF_PRICE, APPLE_10_PERCENT_DISCOUNT);

    private final BigDecimal discount;
    private final StockItem stockItem;
    private final LocalDate validFrom;
    private final LocalDate validTo;
    private final Contigent contigent;

    public Discount(StockItem stockItem, double discount, LocalDate validFrom, LocalDate validTo) {
        this(stockItem, discount, validFrom, validTo, null);
    }

    public Discount(StockItem stockItem,
                    double discount,
                    LocalDate validFrom,
                    LocalDate validTo,
                    Contigent contigent) {

        this.stockItem = stockItem;
        this.discount = DecimalHelper.bigDecimalFromDoubleValue(discount);
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.contigent = contigent;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public StockItem getStockItem() {
        return stockItem;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public Contigent getContigent() {
        return contigent;
    }

    public boolean isContigentOn() {
        return contigent != null;
    }

    public static class Contigent {
        private final StockItem stockItem;
        private final Integer unit;

        Contigent(StockItem stockItem, Integer unit) {
            this.stockItem = stockItem;
            this.unit = unit;
        }

        public StockItem getStockItem() {
            return stockItem;
        }

        public Integer getUnit() {
            return unit;
        }
    }
}

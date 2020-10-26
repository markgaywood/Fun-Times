package funtimes;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class DecimalHelper {

    public static BigDecimal bigDecimalFromDoubleValue(Double d) {
        return BigDecimal.valueOf(d).setScale(2, RoundingMode.HALF_UP);
    }
}

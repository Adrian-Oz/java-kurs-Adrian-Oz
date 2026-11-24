package lambdas;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.Function;

public class PriceCalculator {
    public static final Function<Product, BigDecimal> BASE_PRICE = p -> p.price();
    public static final Function<BigDecimal, BigDecimal> WITH_VAT = p -> p.multiply(new BigDecimal("1.23"));
    public static final Function<BigDecimal, BigDecimal> WITH_SHIPPING = p -> p.add(new BigDecimal("15"));
    public static final Function<BigDecimal, String> FORMAT =
            p -> NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pl-PL"))
                    .format(p);

}

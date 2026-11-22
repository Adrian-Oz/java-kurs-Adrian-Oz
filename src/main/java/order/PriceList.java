package order;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class PriceList {
    private final TreeMap<Integer, BigDecimal> discountThresholds = new TreeMap<>();

    public void addThreshold(int minQty, BigDecimal discount) {
        discountThresholds.put(minQty, discount);
    }

    public BigDecimal getDiscountFor(int quantity) {
//  floorEntry can return null.
//  return discountThresholds.floorEntry(quantity) == null ? BigDecimal.ZERO : discountThresholds.floorEntry(quantity).getValue();
//  this called floorEntry twice so I stored entry with var
        var entry = discountThresholds.floorEntry(quantity);
        return entry == null ? BigDecimal.ZERO : entry.getValue();
    }

    public Map<Integer, BigDecimal> thresholdsUpTo(int max) {
        return new TreeMap<>(discountThresholds.headMap(max));
    }

    public Optional<Integer> nextThresholdAfter(int quantity) {
        return Optional.ofNullable(discountThresholds.higherKey(quantity));
    }

}

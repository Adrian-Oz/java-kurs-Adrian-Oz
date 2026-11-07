package oop;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal amount, String currency) {

    public Money {
        if (amount == null || amount.signum() < 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("Invalid currency");
        }
        currency = currency.trim().toUpperCase();
        amount = amount.setScale(2, RoundingMode.HALF_UP);
    }


    //Restricting code to validation specified in assigment
    public Money add(Money other) {
        if (!(this.currency.equals(other.currency))) {
            throw new IllegalArgumentException("Currency types doesn't match");
        }
        BigDecimal newAmount = this.amount.add(other.amount);
        return new Money(newAmount, this.currency);
    }

    public Money subtract(Money other) {
        BigDecimal newAmount = this.amount.subtract(other.amount);
        if(newAmount.signum() <0) {
            throw  new IllegalArgumentException("Amount after subtracting must be positive");
        }
        return new Money(newAmount , this.currency);
    }

    public Money multiply(int quantity) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(quantity)),this.currency);
    }

    public Money applyPercent(double percent) {
        BigDecimal totalPercent = BigDecimal.valueOf((100 + percent)/100);

        return new Money(this.amount.multiply(totalPercent),this.currency);
    }

    public boolean isGreaterThan(Money other) {
        return this.amount.compareTo(other.amount) > 0;
    }

    static Money zero(String currency) {
        return new Money(BigDecimal.ZERO , currency);
    }

    static Money of(String amount, String currency) {
        return new Money(new BigDecimal(amount),currency);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s",this.amount,this.currency);
    }
}

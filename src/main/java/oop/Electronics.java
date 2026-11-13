package oop;

import lombok.Getter;

import java.math.BigDecimal;
@Getter
public class Electronics extends Product {

    public Electronics(String sku, String name, Money netPrice, Category category, int warrantyMonths , Supplier supplier) {
        super(sku, name, netPrice, category,supplier);
        this.warrantyMonths = warrantyMonths;
    }

    int warrantyMonths;

    @Override
    public String getDescription() {
        return super.getDescription() + " (Gwarancja " + warrantyMonths + " mies.)";
    }

    @Override
    public double getShippingCost() {
        if (this.getGrossPrice().amount().compareTo(BigDecimal.valueOf(2000)) > 0) {
            return 0;
        }
        return 19.99d;
    }

    @Override
    public Category getCategory() {
        return super.getCategory();
    }

    @Override
    public String getId() {
        return getSku();
    }

    @Override
    public String getLabel() {
        return String.format("%s [%s] - %.2f zł (z wysyłką %.2f zł)", getName(), getCategory(), getGrossPrice().amount(), getTotalPrice().amount());
    }
//     public double getTotalPrice(){
//        return getGrossPrice()+getShippingCost();
//    }

}

package oop;

public class Electronics extends Product {

    public Electronics(String sku, String name, double netPrice, int warrantyMonths) {
        super(sku, name, netPrice);
        this.warrantyMonths = warrantyMonths;
    }

    int warrantyMonths;

    @Override
    public String getDescription() {
        return super.getDescription() + " (Gwarancja " + warrantyMonths + " mies.)";
    }

    @Override
    public double getShippingCost() {
        if (this.getGrossPrice() > 2000) {
            return 0;
        }
        return 19.99d;
    }

    @Override
    public String getCategory() {
        return "Electronics";
    }

//     public double getTotalPrice(){
//        return getGrossPrice()+getShippingCost();
//    }

}

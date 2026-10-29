package oop;

public class Electronics extends Product {

    public Electronics(Product other, int warrantyMonths) {
        super(other);
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
        return super.getShippingCost();
    }

//     public double getTotalPrice(){
//        return getGrossPrice()+getShippingCost();
//    }

}

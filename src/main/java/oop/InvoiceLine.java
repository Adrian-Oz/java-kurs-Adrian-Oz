package oop;

public record InvoiceLine(ProductDTO product, int quantity) {


    public Money netValue() {
        return product.netPrice().multiply(quantity);
    }

    public Money vatValue() {
        return netValue().multiply(product.category().getVatRate());
    }

    public Money grossValue() {
        return netValue().add(vatValue());
    }


}

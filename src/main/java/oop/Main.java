package oop;


public class Main {
    public static void main(String[] args) {


        ProductDTO prod1 = ProductDTO.fromCsvLine("ELEC-001;Laptop;10.00 Zł;ELECTRONICS");
        ProductDTO prod2 = ProductDTO.fromCsvLine("FOOD-002;Rum;20.00 Zł;ALCOHOL");
        ProductDTO prod3 = ProductDTO.fromCsvLine("FOOD-003;Banany;30.00 Zł;FOOD");
        ProductDTO prod4 = ProductDTO.fromCsvLine("BOOK-004;ShadowSlave;40.00 Zł;BOOKS");


        InvoiceLine line1 = new InvoiceLine(prod1, 4);
        InvoiceLine line2 = new InvoiceLine(prod2, 10);
        InvoiceLine line3 = new InvoiceLine(prod3, 5);
        InvoiceLine line4 = new InvoiceLine(prod4, 20);


        Invoice inv1 = new Invoice();
        inv1.addLine(line1);
        inv1.addLine(line2);
        inv1.addLine(line3);
        inv1.addLine(line4);

        System.out.println(inv1.getVatSummary());

        System.out.println(inv1.render());

    }
}

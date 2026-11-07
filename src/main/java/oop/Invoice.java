package oop;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Invoice {

    private final List<InvoiceLine> invoice = new ArrayList<>();

    public void addLine(InvoiceLine line) {
        invoice.add(line);
    }

    public Money getTotalNet() {
        Money sum = Money.zero("zł");
        for (InvoiceLine line : invoice) {
            sum = sum.add(line.netValue());
        }
        return sum;
    }

    public Money getTotalVat() {
        Money sum = Money.zero("zł");
        for (InvoiceLine line : invoice) {
            sum = sum.add(line.vatValue());
        }
        return sum;
    }

    public Money getTotalGross() {
        Money sum = Money.zero("zł");
        for (InvoiceLine line : invoice) {
            sum = sum.add(line.grossValue());
        }
        return sum;
    }

    public Map<Category, Money> getVatSummary() {
        Map<Category, Money> vatSummary = new HashMap<>();
        for (InvoiceLine line : invoice) {
            vatSummary.merge(line.product().category(), line.vatValue(), Money::add);

        }
        return vatSummary;
    }

    public String render() {


        String header = """
                ==============================================
                                 INVOICE
                ==============================================
                """;


        StringBuilder invoiceString = new StringBuilder();
        invoiceString.append(header).append(String.format("%-25s %5s %12s%n", "Product", "Qty", "Net"));
        for (InvoiceLine line : invoice) {
            invoiceString.append((String.format("%-25s %5s %12s%n", line.product().sku(), line.quantity(), line.netValue())));
        }
        invoiceString.append(String.format("%-33s %5s %n", "Total net value:", getTotalNet()));
        invoiceString.append(String.format("%-33s %5s %n", "Total net gross:", getTotalGross()));
        return invoiceString.toString();

    }


}

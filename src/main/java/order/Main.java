package order;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        catalog.add(new Product("ELEC-2","PC2",new BigDecimal("100"),"ELECTRONICS"));
        catalog.add(new Product("ELEC-103","PC103",new BigDecimal("200"),"ELECTRONICS"));
        catalog.add(new Product("ELEC-22","PC22",new BigDecimal("3000.02"),"ELECTRONICS"));
        catalog.add(new Product("ELEC-0","PC0",new BigDecimal("12.34"),"ELECTRONICS"));
        catalog.add(new Product("FOOD-10","FOOD1",new BigDecimal("11.20"),"FOOD"));
        catalog.add(new Product("FOOD-30","FOOD2",new BigDecimal("3.06"),"FOOD"));
        catalog.add(new Product("DIGI-33","DIGI-33",new BigDecimal("999"),"DIGITAL"));
        catalog.add(new Product("DIGI-32","DIGI-32",new BigDecimal("101"),"DIGITAL"));

        PriceList priceList  = new PriceList();
        priceList.addThreshold(3,new BigDecimal("0.05"));
        priceList.addThreshold(5,new BigDecimal("0.20"));
        priceList.addThreshold(8,new BigDecimal("0.50"));

        Order o1 = new Order("1","Adrian",4, List.of("ELEC-2","ELEC-2","ELEC-2","ELEC-103"));
        Order o2 = new Order("2","Adrian",1, List.of("ELEC-0","ELEC-2","ELEC-2","ELEC-103","DIGI-32","DIGI-33","FOOD-10","FOOD-30"));
        Order o3 = new Order("3","Adrian",40, List.of("ELEC-2","ELEC-2"));
        Order o4 = new Order("4","Adrian",404, List.of("ELEC-2","ELEC-2","ELEC-2","ELEC-103","ELEC-103","ELEC-103"));
        Order o5 = new Order("5","Adrian",50, List.of("ELEC-2"));

        OrderQueue orderQueue = new OrderQueue();
        orderQueue.submit(o1);
        orderQueue.submit(o2);
        orderQueue.submit(o3);
        orderQueue.submit(o4);
        orderQueue.submit(o5);
        OrderService orderService = new OrderService(catalog,priceList,orderQueue);

        System.out.println("Processed: " + orderQueue.processNext());
        System.out.println("Processed: " + orderQueue.processNext());
        System.out.println("Processed: " + orderQueue.processNext());

        System.out.println("Undo" + orderQueue.undoLast());




        System.out.println("=========================================");
        System.out.println("ORDER SERVICE");
        System.out.println("=========================================");

        System.out.println("Unavailable SKUs in order: " + orderService.findUnavailableSkus(o1));
        System.out.println("Calculated order total: " + orderService.calculateTotal(o1));
        System.out.println("Product occurrence count across pending orders: " + orderService.countProductsInPendingOrders());

        System.out.println("=========================================");
        System.out.println("CATALOG");
        System.out.println("=========================================");

        System.out.println("Products filtered by category FOOD: " + catalog.findByCategory("FOOD"));
        System.out.println("All available categories: " + catalog.getCategories());
        System.out.println("Products sorted alphabetically by name: " + catalog.sortedByName());
        System.out.println("Products sorted by price (descending): " + catalog.sortedByPriceDesc());
        System.out.println("Products grouped by category: " + catalog.groupByCategory());
        System.out.println("Product count per category: " + catalog.countByCategory());
        System.out.println("Total product value per category: " + catalog.totalValueByCategory());
        System.out.println("Cheapest product: " + catalog.findCheapest());
        System.out.println("Most expensive product: " + catalog.findMostExpensive());

        System.out.println("=========================================");
        System.out.println("ORDER QUEUE");
        System.out.println("=========================================");

        System.out.println("Completed order history (maximum 10): " + orderQueue.getHistory());
        System.out.println("Currently pending orders: " + orderQueue.getPending());
        System.out.println("Customers with pending orders: " + orderQueue.findCustomersWithPendingOrders());
        System.out.println("Orders grouped by status: " + orderQueue.groupByStatus());
        System.out.println("Next order to be processed (highest priority): " + orderQueue.peekNext());

        System.out.println("=========================================");
        System.out.println("PRICE LIST");
        System.out.println("=========================================");

        System.out.println("Discount thresholds up to quantity 6: " + priceList.thresholdsUpTo(6));
        System.out.println("Next discount threshold after quantity 0: " + priceList.nextThresholdAfter(0));

        System.out.println("=========================================");















    }

}

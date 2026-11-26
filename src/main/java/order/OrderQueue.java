package order;

import java.util.*;

public class OrderQueue {
    private final PriorityQueue<Order> pending = new PriorityQueue<>(Comparator.comparingInt(Order::priority).reversed());
    private final Deque<Order> completed = new ArrayDeque<>();
    private final Map<String, Status> statuses = new HashMap<>();

    public void submit(Order o) {
        pending.add(o);
        statuses.put(o.id(), Status.NEW);
    }

    private void addToHistory(Order o) {
        completed.offerFirst(o);
        if (completed.size() > 10) {
            completed.pollLast();
        }
    }


    public Optional<Order> processNext() {
        Optional<Order> order = Optional.ofNullable(pending.poll());
        order.ifPresent((o) -> {
            statuses.replace(o.id(), Status.COMPLETED);
            addToHistory(o);
        });
        return order;
    }

    public Optional<Order> peekNext() {
        return Optional.ofNullable(pending.peek());
    }

    public Optional<Order> undoLast() {
        Optional<Order> order = Optional.ofNullable(completed.pollFirst());
        order.ifPresent((o) -> {
            submit(o);
//          Unclear about the status change here
            statuses.replace(o.id(), Status.NEW);

        });
        return order;
    }

    public List<Order> getHistory() {
        return new ArrayList<>(completed);
    }

    public List<Order> getPending() {
        return new ArrayList<>(pending);
    }

    public Map<Status, List<String>> groupByStatus() {
        EnumMap<Status, List<String>> groups = new EnumMap<>(Status.class);
        for (String sku : statuses.keySet()) {
            groups.computeIfAbsent(statuses.get(sku), (status) -> new ArrayList<>()).add(sku);
        }
        return groups;
    }

    public Set<String> findCustomersWithPendingOrders() {
        Set<String> currentCustomers = new HashSet<>();
        for (Order order : pending) {
            currentCustomers.add(order.customer());
        }
        return currentCustomers;
    }
}

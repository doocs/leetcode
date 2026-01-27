class OrderManagementSystem {

    private record Key(String orderType, int price) {
    }

    private final Map<Integer, String> orderTypeMap;
    private final Map<Integer, Integer> priceMap;
    private final Map<Key, List<Integer>> t;

    public OrderManagementSystem() {
        orderTypeMap = new HashMap<>();
        priceMap = new HashMap<>();
        t = new HashMap<>();
    }

    public void addOrder(int orderId, String orderType, int price) {
        orderTypeMap.put(orderId, orderType);
        priceMap.put(orderId, price);
        var key = new Key(orderType, price);
        t.computeIfAbsent(key, _ -> new ArrayList<>()).add(orderId);
    }

    public void modifyOrder(int orderId, int newPrice) {
        var orderType = orderTypeMap.get(orderId);
        var oldPrice = priceMap.get(orderId);
        priceMap.put(orderId, newPrice);
        t.get(new Key(orderType, oldPrice)).remove((Integer) orderId);
        t.computeIfAbsent(new Key(orderType, newPrice), _ -> new ArrayList<>()).add(orderId);
    }

    public void cancelOrder(int orderId) {
        var orderType = orderTypeMap.remove(orderId);
        var price = priceMap.remove(orderId);
        t.get(new Key(orderType, price)).remove((Integer) orderId);
    }

    public int[] getOrdersAtPrice(String orderType, int price) {
        var list = t.getOrDefault(new Key(orderType, price), List.of());
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}

/**
 * Your OrderManagementSystem object will be instantiated and called as such:
 * OrderManagementSystem obj = new OrderManagementSystem();
 * obj.addOrder(orderId,orderType,price);
 * obj.modifyOrder(orderId,newPrice);
 * obj.cancelOrder(orderId);
 * int[] param_4 = obj.getOrdersAtPrice(orderType,price);
 */

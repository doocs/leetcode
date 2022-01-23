class StockPrice {
    private int lastTs;
    private Map<Integer, Integer> mp = new HashMap<>();
    private TreeMap<Integer, Integer> counter = new TreeMap<>();

    public StockPrice() {

    }
    
    public void update(int timestamp, int price) {
        if (mp.containsKey(timestamp)) {
            int oldPrice = mp.get(timestamp);
            counter.put(oldPrice, counter.get(oldPrice) - 1);
            if (counter.get(oldPrice) == 0) {
                counter.remove(oldPrice);
            }
        }
        mp.put(timestamp, price);
        counter.put(price, counter.getOrDefault(price, 0) + 1);
        lastTs = Math.max(lastTs, timestamp);
    }
    
    public int current() {
        return mp.get(lastTs);
    }
    
    public int maximum() {
        return counter.lastKey();
    }
    
    public int minimum() {
        return counter.firstKey();
    }
}

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */
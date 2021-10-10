class StockPrice {
    private int lastTs;
    private PriorityQueue<Integer> mi = new PriorityQueue<>();
    private PriorityQueue<Integer> mx = new PriorityQueue<>(Collections.reverseOrder());
    private Map<Integer, Integer> mp = new HashMap<>();
    private Map<Integer, Integer> counter = new HashMap<>();

    public StockPrice() {

    }
    
    public void update(int timestamp, int price) {
        if (mp.containsKey(timestamp)) {
            int oldPrice = mp.get(timestamp);
            counter.put(oldPrice, counter.get(oldPrice) - 1);
        }
        mp.put(timestamp, price);
        lastTs = Math.max(lastTs, timestamp);
        counter.put(price, counter.getOrDefault(price, 0) + 1);
        mi.offer(price);
        mx.offer(price);
    }
    
    public int current() {
        return mp.get(lastTs);
    }
    
    public int maximum() {
        while (counter.getOrDefault(mx.peek(), 0) == 0) {
            mx.poll();
        }
        return mx.peek();
    }
    
    public int minimum() {
        while (counter.getOrDefault(mi.peek(), 0) == 0) {
            mi.poll();
        }
        return mi.peek();
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
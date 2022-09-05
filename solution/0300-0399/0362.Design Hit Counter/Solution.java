class HitCounter {

    private Map<Integer, Integer> counter;

    /** Initialize your data structure here. */
    public HitCounter() {
        counter = new HashMap<>();
    }

    /**
       Record a hit.
        @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        counter.put(timestamp, counter.getOrDefault(timestamp, 0) + 1);
    }

    /**
       Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        int hits = 0;
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (entry.getKey() + 300 > timestamp) {
                hits += entry.getValue();
            }
        }
        return hits;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
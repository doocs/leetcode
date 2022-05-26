class MapSum {
    private Map<String, Integer> data;
    private Map<String, Integer> t;

    /** Initialize your data structure here. */
    public MapSum() {
        data = new HashMap<>();
        t = new HashMap<>();
    }
    
    public void insert(String key, int val) {
        int old = t.getOrDefault(key, 0);
        t.put(key, val);
        for (int i = 1; i < key.length() + 1; ++i) {
            String k = key.substring(0, i);
            data.put(k, data.getOrDefault(k, 0) + (val - old));
        }
    }
    
    public int sum(String prefix) {
        return data.getOrDefault(prefix, 0);
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
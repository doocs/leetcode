class TimeMap {
    private Map<String, TreeMap<Integer, String>> ktv;

    /** Initialize your data structure here. */
    public TimeMap() {
        ktv = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        ktv.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
    }
    
    public String get(String key, int timestamp) {
        if (!ktv.containsKey(key)) {
            return "";
        }
        TreeMap<Integer, String> tv = ktv.get(key);
        Integer t = tv.floorKey(timestamp);
        return t == null ? "" : tv.get(t);
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
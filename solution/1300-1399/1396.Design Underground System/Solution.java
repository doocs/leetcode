class UndergroundSystem {
    private Map<Integer, Integer> ts = new HashMap<>();
    private Map<Integer, String> names = new HashMap<>();
    private Map<String, int[]> d = new HashMap<>();

    public UndergroundSystem() {

    }
    
    public void checkIn(int id, String stationName, int t) {
        ts.put(id, t);
        names.put(id, stationName);
    }
    
    public void checkOut(int id, String stationName, int t) {
        String key = names.get(id) + "-" + stationName;
        int[] v = d.getOrDefault(key, new int[2]);
        v[0] += t - ts.get(id);
        v[1]++;
        d.put(key, v);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "-" + endStation;
        int[] v = d.get(key);
        return (double) v[0] / v[1];
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
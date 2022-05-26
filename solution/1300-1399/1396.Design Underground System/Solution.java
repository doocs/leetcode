class UndergroundSystem {
    private Map<Integer, String> checkInStation;
    private Map<Integer, Integer> checkInTime;
    private Map<String, int[]> totalTime;

    public UndergroundSystem() {
        checkInStation = new HashMap<>();
        checkInTime = new HashMap<>();
        totalTime = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkInStation.put(id, stationName);
        checkInTime.put(id, t);
    }
    
    public void checkOut(int id, String stationName, int t) {
        int cost = t - checkInTime.remove(id);
        String startStation = checkInStation.remove(id);
        String stations = startStation + "." + stationName;
        int[] times = totalTime.getOrDefault(stations, new int[2]);
        times[0] += cost;
        ++times[1];
        totalTime.put(stations, times);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String stations = startStation + "." + endStation;
        int[] times = totalTime.get(stations);
        return times[0] * 1.0 / times[1];
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
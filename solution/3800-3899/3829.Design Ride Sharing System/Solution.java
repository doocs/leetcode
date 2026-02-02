class RideSharingSystem {
    private int t;
    private TreeSet<int[]> riders;
    private TreeSet<int[]> drivers;
    private Map<Integer, Integer> d;

    public RideSharingSystem() {
        this.t = 0;
        this.riders = new TreeSet<>(
            (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        this.drivers = new TreeSet<>(
            (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        this.d = new HashMap<>();
    }

    public void addRider(int riderId) {
        d.put(riderId, t);
        riders.add(new int[] {t, riderId});
        t++;
    }

    public void addDriver(int driverId) {
        drivers.add(new int[] {t, driverId});
        t++;
    }

    public int[] matchDriverWithRider() {
        if (riders.isEmpty() || drivers.isEmpty()) {
            return new int[] {-1, -1};
        }
        int driverId = drivers.pollFirst()[1];
        int riderId = riders.pollFirst()[1];
        return new int[] {driverId, riderId};
    }

    public void cancelRider(int riderId) {
        Integer time = d.get(riderId);
        if (time != null) {
            riders.remove(new int[] {time, riderId});
        }
    }
}

/**
 * Your RideSharingSystem object will be instantiated and called as such:
 * RideSharingSystem obj = new RideSharingSystem();
 * obj.addRider(riderId);
 * obj.addDriver(driverId);
 * int[] param_3 = obj.matchDriverWithRider();
 * obj.cancelRider(riderId);
 */

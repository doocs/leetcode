class BusBooking {
    private int tot;
    private int minTot;
    private int[][] costData;

    public BusBooking(int n) {
        this.tot = 0;
        this.minTot = 0;
        this.costData = new int[n][4];
    }

    public void toggle(String seat) {
        int i = Integer.parseInt(seat.substring(1)) - 1;
        int j = seat.charAt(0) - 'A';
        if (costData[i][j] > 0) {
            minTot -= 1;
            tot -= costData[i][j];
            costData[i][j] = 0;
            return;
        }
        int cost = 1;
        if ((j == 0 && costData[i][1] > 0) || (j == 3 && costData[i][2] > 0)) {
            cost = 3;
        }
        minTot += 1;
        tot += cost;
        costData[i][j] = cost;
    }

    public int getTotalTime() {
        return tot;
    }

    public int getMinTime() {
        return minTot;
    }
}

/**
 * Your BusBooking object will be instantiated and called as such:
 * BusBooking obj = new BusBooking(n);
 * obj.toggle(seat);
 * int param_2 = obj.getTotalTime();
 * int param_3 = obj.getMinTime();
 */
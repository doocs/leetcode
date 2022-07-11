class Solution {
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        int j = 0, c = 0;
        for (int t : buses) {
            c = capacity;
            while (c > 0 && j < passengers.length && passengers[j] <= t) {
                --c;
                ++j;
            }
        }
        --j;
        int ans = c > 0 ? buses[buses.length - 1] : passengers[j];
        while (j >= 0 && ans == passengers[j]) {
            --ans;
            --j;
        }
        return ans;
    }
}
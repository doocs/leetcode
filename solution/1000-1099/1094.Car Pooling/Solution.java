class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] d = new int[1001];
        for (var trip : trips) {
            int x = trip[0], f = trip[1], t = trip[2];
            d[f] += x;
            d[t] -= x;
        }
        int s = 0;
        for (int x : d) {
            s += x;
            if (s > capacity) {
                return false;
            }
        }
        return true;
    }
}
public class Solution {
    public bool CarPooling(int[][] trips, int capacity) {
        int mx = trips.Max(x => x[2]);
        int[] d = new int[mx + 1];
        foreach (var trip in trips) {
            int x = trip[0], f = trip[1], t = trip[2];
            d[f] += x;
            d[t] -= x;
        }
        int s = 0;
        foreach (var x in d) {
            s += x;
            if (s > capacity) {
                return false;
            }
        }
        return true;
    }
}
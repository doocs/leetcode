class Solution {
    public int buttonWithLongestTime(int[][] events) {
        int ans = events[0][0], t = events[0][1];
        for (int k = 1; k < events.length; ++k) {
            int i = events[k][0], t2 = events[k][1], t1 = events[k - 1][1];
            int d = t2 - t1;
            if (d > t || (d == t && ans > i)) {
                ans = i;
                t = d;
            }
        }
        return ans;
    }
}

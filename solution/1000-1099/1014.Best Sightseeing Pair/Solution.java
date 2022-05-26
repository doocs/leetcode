class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        int res = 0, mx = values[0];
        for (int i = 1; i < values.length; ++i) {
            res = Math.max(res, values[i] - i + mx);
            mx = Math.max(mx, values[i] + i);
        }
        return res;
    }
}
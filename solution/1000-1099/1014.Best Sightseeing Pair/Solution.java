class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        int ans = 0, mx = values[0];
        for (int j = 1; j < values.length; ++j) {
            ans = Math.max(ans, values[j] - j + mx);
            mx = Math.max(mx, values[j] + j);
        }
        return ans;
    }
}
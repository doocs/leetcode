class Solution {
    public long getDescentPeriods(int[] prices) {
        long ans = 0;
        for (int i = 0, n = prices.length; i < n;) {
            int j = i;
            for (; j + 1 < n && prices[j] - prices[j + 1] == 1; ++j);
            int t = j - i + 1;
            ans += (long) t * (t + 1) / 2;
            i = j + 1;
        }
        return ans;
    }
}
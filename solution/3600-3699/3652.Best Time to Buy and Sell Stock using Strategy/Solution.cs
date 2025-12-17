public class Solution {
    public long MaxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.Length;
        long[] s = new long[n + 1];
        long[] t = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            long a = prices[i - 1];
            long b = strategy[i - 1];
            s[i] = s[i - 1] + a * b;
            t[i] = t[i - 1] + a;
        }

        long ans = s[n];
        for (int i = k; i <= n; i++) {
            long cur = s[n] - (s[i] - s[i - k]) + (t[i] - t[i - k / 2]);
            if (cur > ans) {
                ans = cur;
            }
        }

        return ans;
    }
}

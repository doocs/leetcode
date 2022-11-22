public class Solution {
    public int MaxProfit(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.Length; ++i) {
            ans += Math.Max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }
}
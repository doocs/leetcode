public class Solution {
    public int MaxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.Length; ++i)
        {
            int t = prices[i] - prices[i - 1];
            res += Math.Max(t, 0);
        }
        return res;
    }
}
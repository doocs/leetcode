public class Solution {
    public int MaxProfit(int[] prices) {
        int res = 0, mi = prices[0];
        for (int i = 1; i < prices.Length; ++i)
        {
            res = Math.Max(res, prices[i] - mi);
            mi = Math.Min(mi, prices[i]);
        }
        return res;
    }
}
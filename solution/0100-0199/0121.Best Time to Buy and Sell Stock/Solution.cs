public class Solution {
    public int MaxProfit(int[] prices) {
        int ans = 0, mi = prices[0];
        foreach (int v in prices) {
            ans = Math.Max(ans, v - mi);
            mi = Math.Min(mi, v);
        }
        return ans;
    }
}
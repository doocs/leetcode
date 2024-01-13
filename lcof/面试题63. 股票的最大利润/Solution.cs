public class Solution {
    public int MaxProfit(int[] prices) {
        int mi = 1 << 30;
        int ans = 0;
        foreach(int x in prices) {
            ans = Math.Max(ans, x - mi);
            mi = Math.Min(mi, x);
        }
        return ans;
    }
}

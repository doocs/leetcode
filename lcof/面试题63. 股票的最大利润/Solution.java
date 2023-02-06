class Solution {
    public int maxProfit(int[] prices) {
        int mi = 1 << 30, ans = 0;
        for (int x : prices) {
            ans = Math.max(ans, x - mi);
            mi = Math.min(mi, x);
        }
        return ans;
    }
}
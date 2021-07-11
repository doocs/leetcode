class Solution {
    public int maxProfit(int[] prices) {
        int res = 0, mi = prices[0];
        for (int i = 1; i < prices.length; ++i) {
            res = Math.max(res, prices[i] - mi);
            mi = Math.min(mi, prices[i]);
        }
        return res;
    }
}
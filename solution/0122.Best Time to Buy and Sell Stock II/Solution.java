class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (i + 1 < prices.length && prices[i + 1] - prices[i] > 0) {
                maxProfit += prices[i + 1] - prices[i];
            }
        }
        return maxProfit;
    }
}
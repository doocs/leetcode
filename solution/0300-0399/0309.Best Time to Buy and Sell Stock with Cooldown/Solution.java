class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        
        int n = prices.length;
        // buy[i] 表示第i天持有股票，最大利润
        int[] buy = new int[n];
        // sell[i] 表示第i天为持股，最大利润
        int[] sell = new int[n];
        buy[0] = -prices[0];
        sell[0] = 0;
        
        for(int i = 1; i < n; i++) {
            buy[i] = Math.max(buy[i - 1], (i > 1 ? sell[i - 2] : 0) - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        
        return sell[n - 1];
    }
}
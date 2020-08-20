class Solution {
    public int maxProfit(int k, int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
        
        if(k >= prices.length) return maxProfit(prices);
        
		int n = prices.length;
		int[] l = new int[k + 1];
		int[] g = new int[k + 1];

		for (int i = 1; i < n; i++) {
			int diff = prices[i] - prices[i - 1];
			for (int j = k; j >= 1; j--) {
				l[j] = Math.max(l[j], g[j - 1]) + diff;
				g[j] = Math.max(l[j], g[j]);
			}
		}

		return g[k];
    }
    
    public int maxProfit(int[] prices) {
        // 只要有利润就卖，就是最优解。
        int profit = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}
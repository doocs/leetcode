class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int m = prices[0] , l = 0;
        for (int i = 1; i < prices.length; i++) {
            int d = prices[i];
            if (d < m) {
                m = d;
            } else {
                int n = d - m;
                l = n > l ? n : l;
            }
        }
        return l;
    }
}
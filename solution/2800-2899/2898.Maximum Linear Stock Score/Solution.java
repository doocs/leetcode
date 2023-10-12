class Solution {
    public long maxScore(int[] prices) {
        Map<Integer, Long> cnt = new HashMap<>();
        for (int i = 0; i < prices.length; ++i) {
            cnt.merge(prices[i] - i, (long) prices[i], Long::sum);
        }
        long ans = 0;
        for (long v : cnt.values()) {
            ans = Math.max(ans, v);
        }
        return ans;
    }
}
class Solution {
    public int maximumProfit(int[] present, int[] future, int budget) {
        List<int[]> arr = new ArrayList<>();
        for (int i = 0; i < present.length; ++i) {
            if (future[i] > present[i]) {
                arr.add(new int[]{present[i], future[i] - present[i]});
            }
        }
        int[] dp = new int[budget + 1];
        for (int[] e : arr) {
            int v = e[0], w = e[1];
            for (int j = budget; j >= v; --j) {
                dp[j] = Math.max(dp[j], dp[j - v] + w);
            }
        }
        return dp[budget];
    }
}
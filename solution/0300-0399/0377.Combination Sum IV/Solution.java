class Solution {
        public int combinationSum4(int[] n, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        return combinationSum4Dfs(n, dp, target);

    }

    private int combinationSum4Dfs(int[] n, int[] dp, int target) {
        if (target < 0) {
            return 0;
        }

        if (dp[target] != -1) {
            return dp[target];
        }

        int rt = 0;
        for (int v : n) {
            rt += combinationSum4Dfs(n, dp, target - v);
        }

        dp[target] = rt;
        return dp[target];
    }
}
class Solution {
    private int[] nums;
    private Integer[][][] f;
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        int m = 0;
        for (int x : nums) {
            if (x > m) m = x;
        }
        this.f = new Integer[n + 1][m + 1][m + 1];
        return (dfs(n, 0, 0) - 1 + MOD) % MOD;
    }

    private int dfs(int i, int j, int k) {
        if (i == 0) {
            return j == k ? 1 : 0;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int x = nums[i - 1];
        int res = ((dfs(i - 1, j, k) + dfs(i - 1, gcd(x, j), k)) % MOD + dfs(i - 1, j, gcd(x, k)))
            % MOD;
        f[i][j][k] = res;
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
class Solution {
    public int minXor(int[] nums, int k) {
        int n = nums.length;
        int[] g = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            g[i] = g[i - 1] ^ nums[i - 1];
        }

        int[][] f = new int[n + 1][k + 1];
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(f[i], Integer.MAX_VALUE);
        }
        f[0][0] = 0;

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= Math.min(i, k); ++j) {
                for (int h = j - 1; h < i; ++h) {
                    f[i][j] = Math.min(f[i][j], Math.max(f[h][j - 1], g[i] ^ g[h]));
                }
            }
        }

        return f[n][k];
    }
}

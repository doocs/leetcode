class Solution {
    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int n = nums.size();
        int[][] f = new int[n + 1][target + 1];
        final int inf = 1 << 30;
        for (int[] g : f) {
            Arrays.fill(g, -inf);
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            int x = nums.get(i - 1);
            for (int j = 0; j <= target; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= x) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - x] + 1);
                }
            }
        }
        return f[n][target] <= 0 ? -1 : f[n][target];
    }
}
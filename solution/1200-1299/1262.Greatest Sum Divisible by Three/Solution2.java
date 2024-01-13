class Solution {
    public int maxSumDivThree(int[] nums) {
        final int inf = 1 << 30;
        int[] f = new int[] {0, -inf, -inf};
        for (int x : nums) {
            int[] g = f.clone();
            for (int j = 0; j < 3; ++j) {
                g[j] = Math.max(f[j], f[(j - x % 3 + 3) % 3] + x);
            }
            f = g;
        }
        return f[0];
    }
}
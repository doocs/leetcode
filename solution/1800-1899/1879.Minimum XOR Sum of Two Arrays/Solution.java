class Solution {
    public int minimumXORSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] f = new int[n + 1][1 << n];
        for (var g : f) {
            Arrays.fill(g, 1 << 30);
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 1 << n; ++j) {
                for (int k = 0; k < n; ++k) {
                    if ((j >> k & 1) == 1) {
                        f[i][j]
                            = Math.min(f[i][j], f[i - 1][j ^ (1 << k)] + (nums1[i - 1] ^ nums2[k]));
                    }
                }
            }
        }
        return f[n][(1 << n) - 1];
    }
}
class Solution {
    public int minimumXORSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] f = new int[1 << n];
        Arrays.fill(f, 1 << 30);
        f[0] = 0;
        for (int x : nums1) {
            for (int j = (1 << n) - 1; j >= 0; --j) {
                for (int k = 0; k < n; ++k) {
                    if ((j >> k & 1) == 1) {
                        f[j] = Math.min(f[j], f[j ^ (1 << k)] + (x ^ nums2[k]));
                    }
                }
            }
        }
        return f[(1 << n) - 1];
    }
}
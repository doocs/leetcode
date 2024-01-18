class Solution {
    public int minimumXORSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] f = new int[1 << n];
        Arrays.fill(f, 1 << 30);
        f[0] = 0;
        for (int i = 0; i < 1 << n; ++i) {
            int k = Integer.bitCount(i) - 1;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    f[i] = Math.min(f[i], f[i ^ (1 << j)] + (nums1[k] ^ nums2[j]));
                }
            }
        }
        return f[(1 << n) - 1];
    }
}
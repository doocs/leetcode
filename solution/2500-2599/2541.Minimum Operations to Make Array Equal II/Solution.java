class Solution {
    public long minOperations(int[] nums1, int[] nums2, int k) {
        long ans = 0, x = 0;
        for (int i = 0; i < nums1.length; ++i) {
            int a = nums1[i], b = nums2[i];
            if (k == 0) {
                if (a != b) {
                    return -1;
                }
                continue;
            }
            if ((a - b) % k != 0) {
                return -1;
            }
            int y = (a - b) / k;
            ans += Math.abs(y);
            x += y;
        }
        return x == 0 ? ans / 2 : -1;
    }
}
class Solution {
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int s1 = 0, s2 = 0, n = nums1.length;
        for (int i = 0; i < n; ++i) {
            s1 += nums1[i];
            s2 += nums2[i];
        }
        return Math.max(s2 + f(nums1, nums2), s1 + f(nums2, nums1));
    }

    private int f(int[] nums1, int[] nums2) {
        int t = nums1[0] - nums2[0];
        int mx = t;
        for (int i = 1; i < nums1.length; ++i) {
            int v = nums1[i] - nums2[i];
            if (t > 0) {
                t += v;
            } else {
                t = v;
            }
            mx = Math.max(mx, t);
        }
        return mx;
    }
}
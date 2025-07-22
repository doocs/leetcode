class Solution {
    public int maximumMatchingIndices(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int ans = 0;
        for (int k = 0; k < n; ++k) {
            int t = 0;
            for (int i = 0; i < n; ++i) {
                if (nums1[(i + k) % n] == nums2[i]) {
                    ++t;
                }
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}

class Solution {
    public int numberOfPairs(int[] nums1, int[] nums2, int k) {
        int ans = 0;
        for (int x :nums1) {
            for (int y : nums2) {
                if (x % (y * k) == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
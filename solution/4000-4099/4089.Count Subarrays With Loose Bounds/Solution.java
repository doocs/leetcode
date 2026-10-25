class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long ans = 0;
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            int x = nums[r];
            if (x < minK || x > maxK) {
                l = r + 1;
            } else {
                ans += r - l + 1;
            }
        }
        return ans;
    }
}
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int ans = 1;
        int n = nums.length;
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && nums[j - 1] < nums[j]) {
                ++j;
            }
            ans = Math.max(ans, j - i);
            i = j;
        }
        return ans;
    }
}
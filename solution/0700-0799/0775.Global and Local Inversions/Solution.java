class Solution {
    public boolean isIdealPermutation(int[] nums) {
        int mx = 0;
        for (int i = 2; i < nums.length; ++i) {
            mx = Math.max(mx, nums[i - 2]);
            if (mx > nums[i]) {
                return false;
            }
        }
        return true;
    }
}
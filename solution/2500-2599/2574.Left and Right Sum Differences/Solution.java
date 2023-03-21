class Solution {
    public int[] leftRigthDifference(int[] nums) {
        int left = 0, right = Arrays.stream(nums).sum();
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            right -= nums[i];
            ans[i] = Math.abs(left - right);
            left += nums[i];
        }
        return ans;
    }
}
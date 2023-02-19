class Solution {
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int a = nums[n - 1] - nums[2];
        int b = nums[n - 2] - nums[1];
        int c = nums[n - 3] - nums[0];
        return Math.min(a, Math.min(b, c));
    }
}
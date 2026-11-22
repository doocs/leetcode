class Solution {
    public int minRange(int a, int b, int c) {
        int[] nums = {a, b, c};
        Arrays.sort(nums);
        return Math.min(nums[2] - nums[0], nums[1]);
    }
}
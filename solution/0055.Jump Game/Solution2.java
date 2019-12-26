class Solution {

    public boolean canJump(int[] nums) {
        return solution(nums.length - 1, nums);
    }

    private boolean solution(int target, int[] nums) {
        if (target == 0) return true;
        for (int i = target - 1; i >= 0; i--) {
            if (nums[i] + i >= target) {
                return solution(i, nums);
            }
        }
        return false;
    }
}
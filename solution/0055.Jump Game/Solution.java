class Solution {

    public boolean canJump(int[] nums) {
        int count = 0;
        for (int i = nums.length - 2; i >= 0; i --) {
            count = nums[i] > count ? 0 : count + 1;
        }
        return count == 0;
    }
}
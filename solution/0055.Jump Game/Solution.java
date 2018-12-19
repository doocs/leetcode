class Solution {
    public boolean canJump(int[] nums) {
        int count=0;
        for(int i=nums.length-2;i>=0;i--) {
            if (nums[i] > count) count = 0;
            else count++;
        }
        return count==0;
    }
}
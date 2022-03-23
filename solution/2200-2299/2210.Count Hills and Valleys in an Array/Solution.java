class Solution {
    public int countHillValley(int[] nums) {
        int count = 0;
        for(int i = 1,j = 0; i < nums.length-1; i++) {
            if(nums[i] == nums[i+1]) {
                continue;
            }
            if(nums[i] > nums[j] && nums[i] > nums[i+1]) {
                count++;
            }
            if(nums[i] < nums[j] && nums[i] < nums[i+1]){
                count++;
            }
            j = i;
        }
        return count;
    }
}
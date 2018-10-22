class Solution {
    public int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int end = nums.length - 1;
        int i = 0;
        while(i <= end) {
            if(nums[i] == val) {
                nums[i] = nums[end];
                end--;
            }
            else {
                i++;
            }
        }
        return end + 1;
        
    }
}
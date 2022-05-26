class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length<3) return nums.length;
        int pos = 1,flag = 1,last = nums[0];
        for(int i = 1;i<nums.length;i++){
            if (nums[i] == last) flag++;
            else {
                flag = 1;
                last = nums[i];
            }
            if (flag <= 2) nums[pos++] = last;
        }
        return pos;
    }
}
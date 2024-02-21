class Solution {
    public void moveZeroes(int[] nums) {
        int c=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] != 0){
                nums[c] = nums[i];
                c++;
            }
        }
        for(int j=c;j<nums.length;j++){
            nums[j] = 0;
        }
    }
}

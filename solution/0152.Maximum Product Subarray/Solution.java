class Solution {
    public int maxProduct(int[] nums) {
        int max[] = new int[nums.length];
        int min[] = new int[nums.length];
        int res = nums[0];
        max[0] =  min[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            int num = nums[i];
            max[i] = Math.max(Math.max(max[i-1]*num,min[i-1]*num),num);
            min[i] = Math.min(Math.min(min[i-1]*num,max[i-1]*num),num);
            res = Math.max(max[i],res);
        }
        return res;
    }
}
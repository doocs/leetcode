class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] res = new int[n];
        res[0] = nums[0];
        int max = res[0];
        for (int i = 1; i < n; ++i) {
            res[i] = Math.max(res[i - 1] + nums[i], nums[i]);
            max = Math.max(res[i], max);
        }
        
        return max;
        
    }
}
class Solution {
    public int missingNumber(int[] nums) {
        int res = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            res ^= i;
            res ^= nums[i];
        }
        res ^= n;
        return res;
    }
}
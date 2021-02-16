class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0, n = nums.length; i < n; i += 2) {
            res += nums[i];
        }
        return res;
    }
}
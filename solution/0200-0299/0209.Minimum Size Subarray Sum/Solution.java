class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int left = 0, right = 0;
        int sum = 0, res = n + 1;
        while (right < n) {
            sum += nums[right];
            while (sum >= target) {
                res = Math.min(res, right - left + 1);
                sum -= nums[left++];
            }
            ++right;
        }
        return res == n + 1 ? 0 : res;
    }
}
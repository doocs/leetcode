class Solution {
    public long maxSum(int[] nums, int k, int mul) {
        Arrays.sort(nums);
        int n = nums.length;
        long ans = 0;

        for (int i = n - 1; i >= n - k; i--) {
            int m = Math.max(1, mul);
            ans += (long) nums[i] * m;
            mul--;
        }

        return ans;
    }
}
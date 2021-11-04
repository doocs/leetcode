class Solution {
    public int minStartValue(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n];
        preSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        int ans = Integer.MAX_VALUE;
        for (int num : preSum) {
            ans = Math.min(ans, num);
        }
        return ans < 1 ? ans * -1 + 1 : 1;
    }
}
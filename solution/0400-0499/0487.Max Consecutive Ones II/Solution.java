class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                left[i] = i == 0 ? 1 : left[i - 1] + 1;
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (nums[i] == 1) {
                right[i] = i == n - 1 ? 1 : right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int t = 0;
            if (i > 0) {
                t += left[i - 1];
            }
            if (i < n - 1) {
                t += right[i + 1];
            }
            ans = Math.max(ans, t + 1);
        }
        return ans;
    }
}
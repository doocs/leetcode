class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;

        int[] prefix = new int[n];
        int[] suffix = new int[n];

        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (i == 0) prefix[0] = nums[0];
            else prefix[i] = nums[i] == 0 ? 0 : prefix[i - 1] + 1;
            res = Math.max(res, prefix[i]);
        }

        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1) suffix[n - 1] = nums[n - 1];
            else suffix[i] = nums[i] == 0 ? 0 : suffix[i + 1] + 1;
        }

        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                int t = 1;
                if (i > 0) t += prefix[i - 1];
                if (i < n - 1) t += suffix[i + 1]; 
                res = Math.max(res, t);
            }
        }
        return res;
    }
}
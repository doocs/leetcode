class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        int ans = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] >= nums[i - 1]) {
                left[i] = left[i - 1] + 1;
                ans = Math.max(ans, left[i]);
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= nums[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            int a = (i - 1 < 0) ? 0 : left[i - 1];
            int b = (i + 1 >= n) ? 0 : right[i + 1];
            if (i - 1 >= 0 && i + 1 < n && nums[i - 1] > nums[i + 1]) {
                ans = Math.max(ans, Math.max(a + 1, b + 1));
            } else {
                ans = Math.max(ans, a + b + 1);
            }
        }

        return ans;
    }
}

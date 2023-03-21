class Solution {
    public int convertArray(int[] nums) {
        return Math.min(solve(nums), solve(reverse(nums)));
    }

    private int solve(int[] nums) {
        int n = nums.length;
        int[][] f = new int[n + 1][1001];
        for (int i = 1; i <= n; ++i) {
            int mi = 1 << 30;
            for (int j = 0; j <= 1000; ++j) {
                mi = Math.min(mi, f[i - 1][j]);
                f[i][j] = mi + Math.abs(j - nums[i - 1]);
            }
        }
        int ans = 1 << 30;
        for (int x : f[n]) {
            ans = Math.min(ans, x);
        }
        return ans;
    }

    private int[] reverse(int[] nums) {
        for (int i = 0, j = nums.length - 1; i < j; ++i, --j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
        return nums;
    }
}
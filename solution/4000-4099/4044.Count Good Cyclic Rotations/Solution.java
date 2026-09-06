class Solution {
    public int countGoodRotations(int[] nums) {
        int n = nums.length;
        int m = n / 2;

        long l = 0, r = 0;
        for (int i = 0; i < m; i++) {
            l += nums[i];
        }
        for (int i = m; i < n; i++) {
            r += nums[i];
        }

        int ans = l > r ? 1 : 0;

        for (int i = 0; i < n - 1; i++) {
            l -= nums[i];
            r += nums[i];
            l += nums[(i + m) % n];
            r -= nums[(i + m) % n];
            ans += l > r ? 1 : 0;
        }

        return ans;
    }
}
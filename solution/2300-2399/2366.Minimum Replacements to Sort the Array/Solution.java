class Solution {
    public long minimumReplacement(int[] nums) {
        long ans = 0;
        int n = nums.length;
        int mx = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] <= mx) {
                mx = nums[i];
                continue;
            }
            int k = (nums[i] + mx - 1) / mx;
            ans += k - 1;
            mx = nums[i] / k;
        }
        return ans;
    }
}
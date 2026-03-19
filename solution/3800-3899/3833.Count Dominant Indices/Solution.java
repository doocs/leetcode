class Solution {
    public int dominantIndices(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int suf = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] * (n - i - 1) > suf) {
                ans++;
            }
            suf += nums[i];
        }
        return ans;
    }
}

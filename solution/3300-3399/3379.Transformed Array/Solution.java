class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = nums[i] != 0 ? nums[(i + nums[i] % n + n) % n] : 0;
        }
        return ans;
    }
}

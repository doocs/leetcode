class Solution {
    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        int[] cnt = new int[201];
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n >> 1; ++i) {
            if (++cnt[nums[i] + nums[n - i - 1]] == 1) {
                ++ans;
            }
        }
        return ans;
    }
}
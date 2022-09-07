class Solution {
    public int[] distinctNumbers(int[] nums, int k) {
        int[] cnt = new int[100010];
        int x = 0;
        for (int i = 0; i < k; ++i) {
            if (cnt[nums[i]]++ == 0) {
                ++x;
            }
        }
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        ans[0] = x;
        for (int i = k; i < n; ++i) {
            if (--cnt[nums[i - k]] == 0) {
                --x;
            }
            if (cnt[nums[i]]++ == 0) {
                ++x;
            }
            ans[i - k + 1] = x;
        }
        return ans;
    }
}
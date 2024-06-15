class Solution {
    public int minIncrementForUnique(int[] nums) {
        int m = Arrays.stream(nums).max().getAsInt() + nums.length;
        int[] cnt = new int[m];
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = 0;
        for (int i = 0; i < m - 1; ++i) {
            int diff = cnt[i] - 1;
            if (diff > 0) {
                cnt[i + 1] += diff;
                ans += diff;
            }
        }
        return ans;
    }
}

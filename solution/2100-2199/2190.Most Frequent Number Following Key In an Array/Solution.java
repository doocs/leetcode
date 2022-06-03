class Solution {
    public int mostFrequent(int[] nums, int key) {
        int[] cnt = new int[1010];
        int mx = 0, ans = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] == key) {
                int target = nums[i + 1];
                ++cnt[target];
                if (mx < cnt[target]) {
                    mx = cnt[target];
                    ans = nums[i + 1];
                }
            }
        }
        return ans;
    }
}
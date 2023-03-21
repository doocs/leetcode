class Solution {
    private int[] nums;
    private int[] cnt = new int[1010];
    private int ans = -1;
    private int k;

    public int beautifulSubsets(int[] nums, int k) {
        this.k = k;
        this.nums = nums;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= nums.length) {
            ++ans;
            return;
        }
        dfs(i + 1);
        boolean ok1 = nums[i] + k >= cnt.length || cnt[nums[i] + k] == 0;
        boolean ok2 = nums[i] - k < 0 || cnt[nums[i] - k] == 0;
        if (ok1 && ok2) {
            ++cnt[nums[i]];
            dfs(i + 1);
            --cnt[nums[i]];
        }
    }
}
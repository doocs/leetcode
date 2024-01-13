class Solution {
    private int mx;
    private int ans;
    private int[] nums;

    public int countMaxOrSubsets(int[] nums) {
        this.nums = nums;
        dfs(0, 0);
        return ans;
    }

    private void dfs(int u, int t) {
        if (u == nums.length) {
            if (t > mx) {
                mx = t;
                ans = 1;
            } else if (t == mx) {
                ++ans;
            }
            return;
        }
        dfs(u + 1, t);
        dfs(u + 1, t | nums[u]);
    }
}
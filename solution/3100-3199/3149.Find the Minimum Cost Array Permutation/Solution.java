class Solution {
    private Integer[][] f;
    private int[] nums;
    private int[] ans;
    private int n;

    public int[] findPermutation(int[] nums) {
        n = nums.length;
        ans = new int[n];
        this.nums = nums;
        f = new Integer[1 << n][n];
        g(1, 0, 0);
        return ans;
    }

    private int dfs(int mask, int pre) {
        if (mask == (1 << n) - 1) {
            return Math.abs(pre - nums[0]);
        }
        if (f[mask][pre] != null) {
            return f[mask][pre];
        }
        int res = Integer.MAX_VALUE;
        for (int cur = 1; cur < n; ++cur) {
            if ((mask >> cur & 1) == 0) {
                res = Math.min(res, Math.abs(pre - nums[cur]) + dfs(mask | 1 << cur, cur));
            }
        }
        return f[mask][pre] = res;
    }

    private void g(int mask, int pre, int k) {
        ans[k] = pre;
        if (mask == (1 << n) - 1) {
            return;
        }
        int res = dfs(mask, pre);
        for (int cur = 1; cur < n; ++cur) {
            if ((mask >> cur & 1) == 0) {
                if (Math.abs(pre - nums[cur]) + dfs(mask | 1 << cur, cur) == res) {
                    g(mask | 1 << cur, cur, k + 1);
                    break;
                }
            }
        }
    }
}
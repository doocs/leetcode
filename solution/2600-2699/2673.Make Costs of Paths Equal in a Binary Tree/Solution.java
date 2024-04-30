class Solution {
    public int minIncrements(int n, int[] cost) {
        int ans = 0;
        for (int i = n >> 1; i > 0; --i) {
            int l = i << 1, r = i << 1 | 1;
            ans += Math.abs(cost[l - 1] - cost[r - 1]);
            cost[i - 1] += Math.max(cost[l - 1], cost[r - 1]);
        }
        return ans;
    }
}
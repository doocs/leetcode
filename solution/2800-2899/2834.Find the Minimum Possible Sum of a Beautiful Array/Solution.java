class Solution {
    public long minimumPossibleSum(int n, int target) {
        boolean[] vis = new boolean[n + target];
        long ans = 0;
        for (int i = 1; n > 0; --n, ++i) {
            while (vis[i]) {
                ++i;
            }
            ans += i;
            if (target >= i) {
                vis[target - i] = true;
            }
        }
        return ans;
    }
}
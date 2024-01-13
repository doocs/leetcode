class Solution {
public:
    long long minimumPossibleSum(int n, int target) {
        bool vis[n + target];
        memset(vis, false, sizeof(vis));
        long long ans = 0;
        for (int i = 1; n; ++i, --n) {
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
};
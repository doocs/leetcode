class Solution {
public:
    vector<int> cheapestJump(vector<int>& coins, int maxJump) {
        int n = coins.size();
        vector<int> ans;
        if (coins[n - 1] == -1) {
            return ans;
        }
        int f[n];
        const int inf = 1 << 30;
        f[n - 1] = coins[n - 1];
        for (int i = n - 2; ~i; --i) {
            f[i] = inf;
            if (coins[i] != -1) {
                for (int j = i + 1; j < min(n, i + maxJump + 1); ++j) {
                    f[i] = min(f[i], f[j] + coins[i]);
                }
            }
        }
        if (f[0] == inf) {
            return ans;
        }
        for (int i = 0, s = f[0]; i < n; ++i) {
            if (f[i] == s) {
                s -= coins[i];
                ans.push_back(i + 1);
            }
        }
        return ans;
    }
};
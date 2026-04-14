class Solution {
public:
    long long minimumTotalDistance(vector<int>& robot, vector<vector<int>>& factory) {
        ranges::sort(robot);
        ranges::sort(factory);
        using ll = long long;
        vector<vector<ll>> f(robot.size(), vector<ll>(factory.size(), -1));

        auto dfs = [&](this auto&& dfs, int i, int j) -> ll {
            if (i == robot.size()) {
                return 0;
            }
            if (j == factory.size()) {
                return 1e15;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            ll ans = dfs(i, j + 1);
            ll t = 0;
            for (int k = 0; k < factory[j][1]; ++k) {
                if (i + k >= robot.size()) {
                    break;
                }
                t += abs(robot[i + k] - factory[j][0]);
                ans = min(ans, t + dfs(i + k + 1, j + 1));
            }
            f[i][j] = ans;
            return ans;
        };
        return dfs(0, 0);
    }
};

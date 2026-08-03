class Solution {
public:
    string stoneGameIII(vector<int>& stoneValue) {
        int n = stoneValue.size();
        vector<int> f(n, INT_MIN);

        auto dfs = [&](auto&& dfs, int i) -> int {
            if (i >= n) {
                return 0;
            }

            if (f[i] != INT_MIN) {
                return f[i];
            }

            int ans = INT_MIN;
            int s = 0;

            for (int j = i; j < i + 3 && j < n; j++) {
                s += stoneValue[j];
                ans = max(ans, s - dfs(dfs, j + 1));
            }

            return f[i] = ans;
        };

        int res = dfs(dfs, 0);

        if (res == 0) {
            return "Tie";
        }
        return res > 0 ? "Alice" : "Bob";
    }
};
class Solution {
public:
    long long minCost(string s, int encCost, int flatCost) {
        int n = s.size();
        vector<int> pre(n + 1);

        for (int i = 1; i <= n; ++i) {
            pre[i] = pre[i - 1] + (s[i - 1] - '0');
        }

        auto dfs = [&](this auto&& dfs, int l, int r) -> long long {
            int x = pre[r] - pre[l];
            long long res = x ? 1LL * (r - l) * x * encCost : flatCost;

            if ((r - l) % 2 == 0) {
                int m = (l + r) >> 1;
                res = min(res, dfs(l, m) + dfs(m, r));
            }

            return res;
        };

        return dfs(0, n);
    }
};

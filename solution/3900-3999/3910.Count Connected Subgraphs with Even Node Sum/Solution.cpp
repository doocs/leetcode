class Solution {
public:
    int evenSumSubgraphs(vector<int>& nums, vector<vector<int>>& edges) {
        int n = nums.size();
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            g[e[0]].push_back(e[1]);
            g[e[1]].push_back(e[0]);
        }
        int m = (1 << n) - 1;
        int ans = 0;
        int vis;

        auto dfs = [&](this auto dfs, int u) -> void {
            vis |= 1 << u;
            for (int v : g[u]) {
                if (((vis >> v) & 1) == 0) {
                    dfs(v);
                }
            }
        };

        for (int sub = 1; sub <= m; sub++) {
            int s = 0;
            for (int i = 0; i < n; i++) {
                if ((sub >> i) & 1) {
                    s += nums[i];
                }
            }
            if (s % 2 != 0) {
                continue;
            }
            vis = m ^ sub;
            dfs(31 - __builtin_clz(sub));
            if (vis == m) {
                ans++;
            }
        }
        return ans;
    }
};

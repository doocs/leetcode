class Solution {
public:
    vector<int> minimumFlips(int n, vector<vector<int>>& edges, string start, string target) {
        vector<pair<int, int>> g[n];
        for (int i = 0; i < n - 1; ++i) {
            int a = edges[i][0], b = edges[i][1];
            g[a].push_back({b, i});
            g[b].push_back({a, i});
        }
        vector<int> ans;
        auto dfs = [&](this auto&& dfs, int a, int fa) -> bool {
            bool rev = start[a] != target[a];
            for (auto [b, i] : g[a]) {
                if (b != fa && dfs(b, a)) {
                    ans.push_back(i);
                    rev = !rev;
                }
            }
            return rev;
        };
        if (dfs(0, -1)) {
            return {-1};
        }
        ranges::sort(ans);
        return ans;
    }
};

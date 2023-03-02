class Solution {
public:
    vector<int> countSubTrees(int n, vector<vector<int>>& edges, string labels) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<int> ans(n);
        int cnt[26]{};
        function<void(int, int)> dfs = [&](int i, int fa) {
            int k = labels[i] - 'a';
            ans[i] -= cnt[k];
            cnt[k]++;
            for (int& j : g[i]) {
                if (j != fa) {
                    dfs(j, i);
                }
            }
            ans[i] += cnt[k];
        };
        dfs(0, -1);
        return ans;
    }
};
class Solution {
public:
    vector<int> loudAndRich(vector<vector<int>>& richer, vector<int>& quiet) {
        int n = quiet.size();
        vector<vector<int>> g(n);
        for (auto& r : richer) {
            g[r[1]].push_back(r[0]);
        }
        vector<int> ans(n, -1);
        function<void(int)> dfs = [&](int i) {
            if (ans[i] != -1) {
                return;
            }
            ans[i] = i;
            for (int j : g[i]) {
                dfs(j);
                if (quiet[ans[j]] < quiet[ans[i]]) {
                    ans[i] = ans[j];
                }
            }
        };
        for (int i = 0; i < n; ++i) {
            dfs(i);
        }
        return ans;
    }
};
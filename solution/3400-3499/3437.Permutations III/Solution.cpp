class Solution {
public:
    vector<vector<int>> permute(int n) {
        vector<vector<int>> ans;
        vector<bool> vis(n);
        vector<int> t;
        auto dfs = [&](this auto&& dfs, int i) -> void {
            if (i >= n) {
                ans.push_back(t);
                return;
            }
            for (int j = 1; j <= n; ++j) {
                if (!vis[j] && (i == 0 || t[i - 1] % 2 != j % 2)) {
                    vis[j] = true;
                    t.push_back(j);
                    dfs(i + 1);
                    t.pop_back();
                    vis[j] = false;
                }
            }
        };
        dfs(0);
        return ans;
    }
};

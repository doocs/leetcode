class Solution {
public:
    vector<int> restoreArray(vector<vector<int>>& adjacentPairs) {
        unordered_map<int, vector<int>> g;
        for (auto& e : adjacentPairs) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        int n = adjacentPairs.size() + 1;
        vector<int> ans;
        function<void(int, int)> dfs = [&](int i, int fa) {
            ans.emplace_back(i);
            for (int& j : g[i]) {
                if (j != fa) {
                    dfs(j, i);
                }
            }
        };
        for (auto& [i, v] : g) {
            if (v.size() == 1) {
                dfs(i, 1e6);
                break;
            }
        }
        return ans;
    }
};
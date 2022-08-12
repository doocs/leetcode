class Solution {
public:
    vector<int> restoreArray(vector<vector<int>>& adjacentPairs) {
        int n = adjacentPairs.size() + 1;
        unordered_map<int, vector<int>> g;
        for (auto& e : adjacentPairs) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<int> ans(n);
        for (auto& [k, v] : g) {
            if (v.size() == 1) {
                ans[0] = k;
                ans[1] = v[0];
                break;
            }
        }
        for (int i = 2; i < n; ++i) {
            auto v = g[ans[i - 1]];
            ans[i] = v[0] == ans[i - 2] ? v[1] : v[0];
        }
        return ans;
    }
};
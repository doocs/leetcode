class Solution {
public:
    vector<int> findSubtreeSizes(vector<int>& parent, string s) {
        int n = s.size();
        vector<vector<int>> g(n);
        vector<int> d[26];
        for (int i = 1; i < n; ++i) {
            g[parent[i]].push_back(i);
        }
        vector<int> ans(n);
        vector<array<int, 3>> stk{{0, -1, 0}};
        while (!stk.empty()) {
            auto cur = stk.back();
            stk.pop_back();
            int i = cur[0], fa = cur[1], state = cur[2];
            int idx = s[i] - 'a';
            if (state == 0) {
                ans[i] = 1;
                d[idx].push_back(i);
                stk.push_back({i, fa, 1});
                for (int j : g[i]) {
                    stk.push_back({j, i, 0});
                }
            } else {
                int k = d[idx].size() > 1 ? d[idx][d[idx].size() - 2] : fa;
                if (k >= 0) {
                    ans[k] += ans[i];
                }
                d[idx].pop_back();
            }
        }
        return ans;
    }
};

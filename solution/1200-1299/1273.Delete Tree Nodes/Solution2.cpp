class Solution {
public:
    int deleteTreeNodes(int nodes, vector<int>& parent, vector<int>& value) {
        vector<vector<int>> g(nodes);
        for (int i = 1; i < nodes; ++i) {
            g[parent[i]].emplace_back(i);
        }
        vector<int> sum(nodes), cnt(nodes);
        vector<pair<int, int>> stk{{0, 0}};
        while (!stk.empty()) {
            auto [i, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.emplace_back(i, 1);
                for (int j : g[i]) {
                    stk.emplace_back(j, 0);
                }
            } else {
                int s = value[i], m = 1;
                for (int j : g[i]) {
                    s += sum[j];
                    m += cnt[j];
                }
                if (s == 0) {
                    m = 0;
                }
                sum[i] = s;
                cnt[i] = m;
            }
        }
        return cnt[0];
    }
};

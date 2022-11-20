class Solution {
public:
    int minCostII(vector<vector<int>>& costs) {
        int n = costs.size(), k = costs[0].size();
        vector<int> f = costs[0];
        for (int i = 1; i < n; ++i) {
            vector<int> g = costs[i];
            for (int j = 0; j < k; ++j) {
                int t = INT_MAX;
                for (int h = 0; h < k; ++h) {
                    if (h != j) {
                        t = min(t, f[h]);
                    }
                }
                g[j] += t;
            }
            f = move(g);
        }
        return *min_element(f.begin(), f.end());
    }
};
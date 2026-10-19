class Solution {
public:
    int findColoredRoot(vector<vector<int>>& edges, string colors) {
        int d[128]{};
        d['W'] = 0;
        d['R'] = 1;
        d['B'] = 2;
        int n = colors.size();
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        int ans = -1;
        for (int u = 0; u < n; ++u) {
            auto& vs = g[u];
            if (vs.size() > 3) {
                return -1;
            }
            int cnt[3]{};
            for (int v : vs) {
                ++cnt[d[colors[v]]];
            }
            int c = d[colors[u]];
            if (cnt[c] > 0 || cnt[(c + 2) % 3] > 1) {
                return -1;
            }
            if (vs.size() < 3 && cnt[(c + 2) % 3] == 0) {
                ans = u;
            }
        }
        return ans;
    }
};

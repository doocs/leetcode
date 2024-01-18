class Solution {
public:
    int maximumSubtreeSize(vector<vector<int>>& edges, vector<int>& colors) {
        int n = edges.size() + 1;
        vector<int> g[n];
        vector<int> size(n, 1);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        int ans = 0;
        function<bool(int, int)> dfs = [&](int a, int fa) {
            bool ok = true;
            for (int b : g[a]) {
                if (b != fa) {
                    bool t = dfs(b, a);
                    ok = ok && colors[a] == colors[b] && t;
                    size[a] += size[b];
                }
            }
            if (ok) {
                ans = max(ans, size[a]);
            }
            return ok;
        };
        dfs(0, -1);
        return ans;
    }
};
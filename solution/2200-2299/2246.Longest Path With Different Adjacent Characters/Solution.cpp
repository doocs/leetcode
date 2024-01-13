class Solution {
public:
    int longestPath(vector<int>& parent, string s) {
        int n = parent.size();
        vector<int> g[n];
        for (int i = 1; i < n; ++i) {
            g[parent[i]].push_back(i);
        }
        int ans = 0;
        function<int(int)> dfs = [&](int i) -> int {
            int mx = 0;
            for (int j : g[i]) {
                int x = dfs(j) + 1;
                if (s[i] != s[j]) {
                    ans = max(ans, mx + x);
                    mx = max(mx, x);
                }
            }
            return mx;
        };
        dfs(0);
        return ans + 1;
    }
};
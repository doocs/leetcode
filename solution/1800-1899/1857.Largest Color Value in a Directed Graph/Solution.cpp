class Solution {
public:
    int largestPathValue(string colors, vector<vector<int>>& edges) {
        int n = colors.size();
        vector<vector<int>> g(n);
        vector<int> indeg(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            ++indeg[b];
        }
        queue<int> q;
        vector<vector<int>> dp(n, vector<int>(26));
        for (int i = 0; i < n; ++i) {
            if (indeg[i] == 0) {
                q.push(i);
                int c = colors[i] - 'a';
                dp[i][c]++;
            }
        }
        int cnt = 0;
        int ans = 1;
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            ++cnt;
            for (int j : g[i]) {
                if (--indeg[j] == 0) q.push(j);
                int c = colors[j] - 'a';
                for (int k = 0; k < 26; ++k) {
                    dp[j][k] = max(dp[j][k], dp[i][k] + (c == k));
                    ans = max(ans, dp[j][k]);
                }
            }
        }
        return cnt == n ? ans : -1;
    }
};
class Solution {
public:
    int minimumTime(int n, vector<vector<int>>& relations, vector<int>& time) {
        vector<vector<int>> g(n);
        vector<int> indeg(n);
        for (auto& e : relations) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].push_back(b);
            ++indeg[b];
        }
        queue<int> q;
        vector<int> dp(n);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int v = indeg[i], t = time[i];
            if (v == 0) {
                q.push(i);
                dp[i] = t;
                ans = max(ans, t);
            }
        }
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            for (int j : g[i]) {
                if (--indeg[j] == 0) q.push(j);
                dp[j] = max(dp[j], dp[i] + time[j]);
                ans = max(ans, dp[j]);
            }
        }
        return ans;
    }
};
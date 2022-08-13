class Solution {
public:
    bool makesquare(vector<int>& matchsticks) {
        int s = 0, mx = 0;
        for (int& v : matchsticks) {
            s += v;
            mx = max(mx, v);
        }
        int x = s / 4, mod = s % 4;
        if (mod != 0 || x < mx) return false;
        sort(matchsticks.begin(), matchsticks.end(), greater<int>());
        vector<int> edges(4);
        return dfs(0, x, matchsticks, edges);
    }

    bool dfs(int u, int x, vector<int>& matchsticks, vector<int>& edges) {
        if (u == matchsticks.size()) return true;
        for (int i = 0; i < 4; ++i) {
            if (i > 0 && edges[i - 1] == edges[i]) continue;
            edges[i] += matchsticks[u];
            if (edges[i] <= x && dfs(u + 1, x, matchsticks, edges)) return true;
            edges[i] -= matchsticks[u];
        }
        return false;
    }
};

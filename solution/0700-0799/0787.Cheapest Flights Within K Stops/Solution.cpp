class Solution {
public:
    vector<vector<int>> memo;
    vector<vector<int>> g;
    int dst;
    int inf = 1e6;

    int findCheapestPrice(int n, vector<vector<int>>& flights, int src, int dst, int k) {
        n += 10;
        memo.resize(n, vector<int>(n, -1));
        g.resize(n, vector<int>(n));
        for (auto& e : flights) g[e[0]][e[1]] = e[2];
        this->dst = dst;
        int ans = dfs(src, k + 1);
        return ans >= inf ? -1 : ans;
    }

    int dfs(int u, int k) {
        if (memo[u][k] != -1) return memo[u][k];
        if (u == dst) return 0;
        if (k <= 0) return inf;
        int ans = inf;
        for (int v = 0; v < g[u].size(); ++v)
            if (g[u][v] > 0)
                ans = min(ans, dfs(v, k - 1) + g[u][v]);
        memo[u][k] = ans;
        return memo[u][k];
    }
};
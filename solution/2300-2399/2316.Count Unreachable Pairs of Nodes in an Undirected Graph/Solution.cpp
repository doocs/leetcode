class Solution {
public:
    vector<vector<int>> g;
    vector<bool> vis;

    long long countPairs(int n, vector<vector<int>>& edges) {
        vis.resize(n);
        g.resize(n, vector<int>());
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<int> arr;
        for (int i = 0; i < n; ++i)
            if (!vis[i]) arr.push_back(dfs(i));
        long long ans = 0;
        int t = 0;
        for (int& v : arr) {
            t += v;
            ans += 1ll * v * (n - t);
        }
        return ans;
    }

    int dfs(int i) {
        int res = 1;
        vis[i] = true;
        for (int j : g[i])
            if (!vis[j]) res += dfs(j);
        return res;
    }
};
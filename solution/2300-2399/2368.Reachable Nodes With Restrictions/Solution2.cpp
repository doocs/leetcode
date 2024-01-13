class Solution {
public:
    int reachableNodes(int n, vector<vector<int>>& edges, vector<int>& restricted) {
        vector<vector<int>> g(n);
        vector<bool> vis(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        for (int v : restricted) vis[v] = true;
        queue<int> q{{0}};
        int ans = 0;
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            ++ans;
            vis[i] = true;
            for (int j : g[i])
                if (!vis[j]) q.push(j);
        }
        return ans;
    }
};
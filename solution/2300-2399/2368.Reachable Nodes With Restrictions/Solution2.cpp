class Solution {
public:
    int reachableNodes(int n, vector<vector<int>>& edges, vector<int>& restricted) {
        vector<int> g[n];
        vector<int> vis(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        for (int i : restricted) {
            vis[i] = true;
        }
        queue<int> q{{0}};
        int ans = 0;
        for (vis[0] = true; !q.empty(); ++ans) {
            int i = q.front();
            q.pop();
            for (int j : g[i]) {
                if (!vis[j]) {
                    vis[j] = true;
                    q.push(j);
                }
            }
        }
        return ans;
    }
};
class Solution {
public:
    bool validPath(int n, vector<vector<int>>& edges, int source, int destination) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        queue<int> q{{source}};
        vector<bool> vis(n);
        vis[source] = true;
        while (q.size()) {
            int i = q.front();
            q.pop();
            if (i == destination) {
                return true;
            }
            for (int j : g[i]) {
                if (!vis[j]) {
                    vis[j] = true;
                    q.push(j);
                }
            }
        }
        return false;
    }
};

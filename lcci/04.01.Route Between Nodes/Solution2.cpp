class Solution {
public:
    bool findWhetherExistsPath(int n, vector<vector<int>>& graph, int start, int target) {
        vector<int> g[n];
        vector<bool> vis(n);
        for (auto& e : graph) {
            g[e[0]].push_back(e[1]);
        }
        queue<int> q{{start}};
        vis[start] = true;
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            if (i == target) {
                return true;
            }
            for (int j : g[i]) {
                if (!vis[j]) {
                    q.push(j);
                    vis[j] = true;
                }
            }
        }
        return false;
    }
};
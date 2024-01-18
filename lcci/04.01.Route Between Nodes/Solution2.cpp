class Solution {
public:
    bool findWhetherExistsPath(int n, vector<vector<int>>& graph, int start, int target) {
        unordered_map<int, vector<int>> g;
        for (auto& e : graph) g[e[0]].push_back(e[1]);
        queue<int> q{{start}};
        unordered_set<int> vis{{start}};
        while (!q.empty()) {
            int u = q.front();
            if (u == target) return true;
            q.pop();
            for (int v : g[u]) {
                if (!vis.count(v)) {
                    vis.insert(v);
                    q.push(v);
                }
            }
        }
        return false;
    }
};
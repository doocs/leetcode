class Solution {
public:
    bool findWhetherExistsPath(int n, vector<vector<int>>& graph, int start, int target) {
        unordered_map<int, vector<int>> g;
        for (auto& e : graph) g[e[0]].push_back(e[1]);
        unordered_set<int> vis{{start}};
        return dfs(start, target, g, vis);
    }

    bool dfs(int u, int& target, unordered_map<int, vector<int>>& g, unordered_set<int>& vis) {
        if (u == target) return true;
        for (int& v : g[u]) {
            if (!vis.count(v)) {
                vis.insert(v);
                if (dfs(v, target, g, vis)) return true;
            }
        }
        return false;
    }
};
class Solution {
public:
    bool isBipartite(vector<vector<int>>& graph) {
        int n = graph.size();
        vector<int> color(n);
        auto dfs = [&](this auto&& dfs, int a, int c) -> bool {
            color[a] = c;
            for (int b : graph[a]) {
                if (color[b] == c || (color[b] == 0 && !dfs(b, -c))) {
                    return false;
                }
            }
            return true;
        };
        for (int i = 0; i < n; ++i) {
            if (color[i] == 0 && !dfs(i, 1)) {
                return false;
            }
        }
        return true;
    }
};

class Solution {
public:
    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        unordered_map<int, vector<int>> g;
        for (auto& e : dislikes) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<int> color(n);
        function<bool(int, int)> dfs = [&](int i, int c) -> bool {
            color[i] = c;
            for (int j : g[i]) {
                if (!color[j] && !dfs(j, 3 - c)) return false;
                if (color[j] == c) return false;
            }
            return true;
        };
        for (int i = 0; i < n; ++i) {
            if (!color[i] && !dfs(i, 1)) return false;
        }
        return true;
    }
};
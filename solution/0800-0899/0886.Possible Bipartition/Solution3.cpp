class Solution {
public:
    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        vector<vector<int>> g(n);
        for (auto& e : dislikes) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<int> color(n);
        for (int start = 0; start < n; ++start) {
            if (color[start]) {
                continue;
            }
            color[start] = 1;
            vector<int> stk{start};
            while (!stk.empty()) {
                int i = stk.back();
                stk.pop_back();
                for (int j : g[i]) {
                    if (color[j] == color[i]) {
                        return false;
                    }
                    if (color[j] == 0) {
                        color[j] = 3 - color[i];
                        stk.push_back(j);
                    }
                }
            }
        }
        return true;
    }
};

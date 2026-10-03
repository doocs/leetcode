class Solution {
public:
    int reachableNodes(int n, vector<vector<int>>& edges, vector<int>& restricted) {
        vector<vector<int>> g(n);
        vector<int> vis(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        for (int i : restricted) {
            vis[i] = true;
        }
        int ans = 0;
        vector<int> stk{0};
        while (!stk.empty()) {
            int i = stk.back();
            stk.pop_back();
            if (vis[i]) {
                continue;
            }
            vis[i] = true;
            ++ans;
            for (int j : g[i]) {
                if (!vis[j]) {
                    stk.emplace_back(j);
                }
            }
        }
        return ans;
    }
};

class Solution {
public:
    vector<int> remainingMethods(int n, int k, vector<vector<int>>& invocations) {
        vector<vector<int>> f(n), g(n);
        for (const auto& e : invocations) {
            int a = e[0], b = e[1];
            f[a].push_back(b);
            f[b].push_back(a);
            g[a].push_back(b);
        }
        vector<char> suspicious(n), vis(n);
        suspicious[k] = 1;
        vector<int> stk{k};
        while (!stk.empty()) {
            int i = stk.back();
            stk.pop_back();
            for (int j : g[i]) {
                if (!suspicious[j]) {
                    suspicious[j] = 1;
                    stk.push_back(j);
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            if (suspicious[i] || vis[i]) {
                continue;
            }
            vis[i] = 1;
            stk.push_back(i);
            while (!stk.empty()) {
                int u = stk.back();
                stk.pop_back();
                for (int j : f[u]) {
                    if (!vis[j]) {
                        suspicious[j] = 0;
                        vis[j] = 1;
                        stk.push_back(j);
                    }
                }
            }
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            if (!suspicious[i]) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};

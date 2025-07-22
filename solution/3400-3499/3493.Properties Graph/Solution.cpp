class Solution {
public:
    int numberOfComponents(vector<vector<int>>& properties, int k) {
        int n = properties.size();
        unordered_set<int> ss[n];
        vector<int> g[n];
        for (int i = 0; i < n; ++i) {
            for (int x : properties[i]) {
                ss[i].insert(x);
            }
        }
        for (int i = 0; i < n; ++i) {
            auto& s1 = ss[i];
            for (int j = 0; j < i; ++j) {
                auto& s2 = ss[j];
                int cnt = 0;
                for (int x : s1) {
                    if (s2.contains(x)) {
                        ++cnt;
                    }
                }
                if (cnt >= k) {
                    g[i].push_back(j);
                    g[j].push_back(i);
                }
            }
        }
        int ans = 0;
        vector<bool> vis(n);
        auto dfs = [&](this auto&& dfs, int i) -> void {
            vis[i] = true;
            for (int j : g[i]) {
                if (!vis[j]) {
                    dfs(j);
                }
            }
        };
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                dfs(i);
                ++ans;
            }
        }
        return ans;
    }
};

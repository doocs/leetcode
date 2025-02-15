class Solution {
public:
    vector<string> permutation(string S) {
        int n = S.size();
        vector<bool> vis(n);
        string t = S;
        vector<string> ans;
        auto dfs = [&](this auto&& dfs, int i) {
            if (i >= n) {
                ans.emplace_back(t);
                return;
            }
            for (int j = 0; j < n; ++j) {
                if (!vis[j]) {
                    vis[j] = true;
                    t[i] = S[j];
                    dfs(i + 1);
                    vis[j] = false;
                }
            }
        };
        dfs(0);
        return ans;
    }
};

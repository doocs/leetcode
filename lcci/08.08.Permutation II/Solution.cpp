class Solution {
public:
    vector<string> permutation(string S) {
        ranges::sort(S);
        string t = S;
        int n = t.size();
        vector<bool> vis(n);
        vector<string> ans;
        auto dfs = [&](this auto&& dfs, int i) {
            if (i >= n) {
                ans.emplace_back(t);
                return;
            }
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (j == 0 || S[j] != S[j - 1] || vis[j - 1])) {
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

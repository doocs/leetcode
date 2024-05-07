class Solution {
public:
    vector<string> permutation(string S) {
        int n = S.size();
        vector<bool> vis(n);
        vector<string> ans;
        string t;
        function<void(int)> dfs = [&](int i) {
            if (i >= n) {
                ans.push_back(t);
                return;
            }
            for (int j = 0; j < n; ++j) {
                if (vis[j]) {
                    continue;
                }
                vis[j] = true;
                t.push_back(S[j]);
                dfs(i + 1);
                t.pop_back();
                vis[j] = false;
            }
        };
        dfs(0);
        return ans;
    }
};
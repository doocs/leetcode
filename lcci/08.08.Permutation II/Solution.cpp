class Solution {
public:
    vector<string> permutation(string S) {
        vector<char> cs(S.begin(), S.end());
        sort(cs.begin(), cs.end());
        int n = cs.size();
        vector<string> ans;
        vector<bool> vis(n);
        string t;
        function<void(int)> dfs = [&](int i) {
            if (i == n) {
                ans.push_back(t);
                return;
            }
            for (int j = 0; j < n; ++j) {
                if (vis[j] || (j && !vis[j - 1] && cs[j] == cs[j - 1])) {
                    continue;
                }
                vis[j] = true;
                t.push_back(cs[j]);
                dfs(i + 1);
                t.pop_back();
                vis[j] = false;
            }
        };
        dfs(0);
        return ans;
    }
};
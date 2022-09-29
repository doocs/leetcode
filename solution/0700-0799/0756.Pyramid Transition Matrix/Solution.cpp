class Solution {
public:
    int f[7][7];
    unordered_map<string, bool> dp;

    bool pyramidTransition(string bottom, vector<string>& allowed) {
        memset(f, 0, sizeof f);
        for (auto& s : allowed) {
            int a = s[0] - 'A', b = s[1] - 'A';
            f[a][b] |= 1 << (s[2] - 'A');
        }
        return dfs(bottom, "");
    }

    bool dfs(string& s, string t) {
        if (s.size() == 1) {
            return true;
        }
        if (t.size() + 1 == s.size()) {
            return dfs(t, "");
        }
        string k = s + "." + t;
        if (dp.count(k)) {
            return dp[k];
        }
        int a = s[t.size()] - 'A', b = s[t.size() + 1] - 'A';
        int cs = f[a][b];
        for (int i = 0; i < 7; ++i) {
            if ((cs >> i) & 1) {
                if (dfs(s, t + (char) (i + 'A'))) {
                    dp[k] = true;
                    return true;
                }
            }
        }
        dp[k] = false;
        return false;
    }
};
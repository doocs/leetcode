class Solution {
public:
    int d[7][7];
    unordered_map<string, bool> f;

    bool pyramidTransition(string bottom, vector<string>& allowed) {
        memset(d, 0, sizeof(d));
        for (auto& s : allowed) {
            int a = s[0] - 'A', b = s[1] - 'A';
            d[a][b] |= 1 << (s[2] - 'A');
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
        if (f.contains(k)) {
            return f[k];
        }
        int a = s[t.size()] - 'A', b = s[t.size() + 1] - 'A';
        int cs = d[a][b];
        for (int i = 0; i < 7; ++i) {
            if (cs >> i & 1) {
                if (dfs(s, t + (char) (i + 'A'))) {
                    f[k] = true;
                    return true;
                }
            }
        }
        f[k] = false;
        return false;
    }
};

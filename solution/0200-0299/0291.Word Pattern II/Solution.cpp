class Solution {
public:
    bool wordPatternMatch(string pattern, string s) {
        unordered_set<string> vis;
        unordered_map<char, string> d;
        return dfs(0, 0, pattern, s, vis, d);
    }

    bool dfs(int i, int j, string& p, string& s, unordered_set<string>& vis, unordered_map<char, string>& d) {
        int m = p.size(), n = s.size();
        if (i == m && j == n) return true;
        if (i == m || j == n || m - i > n - j) return false;
        char c = p[i];
        for (int k = j + 1; k <= n; ++k) {
            string t = s.substr(j, k - j);
            if (d.count(c) && d[c] == t) {
                if (dfs(i + 1, k, p, s, vis, d)) return true;
            }
            if (!d.count(c) && !vis.count(t)) {
                d[c] = t;
                vis.insert(t);
                if (dfs(i + 1, k, p, s, vis, d)) return true;
                vis.erase(t);
                d.erase(c);
            }
        }
        return false;
    }
};
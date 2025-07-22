class Solution {
public:
    int longestPalindrome(string s, string t) {
        int m = s.size(), n = t.size();
        ranges::reverse(t);
        vector<int> g1 = calc(s), g2 = calc(t);
        int ans = max(ranges::max(g1), ranges::max(g2));
        vector<vector<int>> f(m + 1, vector<int>(n + 1));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (s[i - 1] == t[j - 1]) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                    ans = max(ans, f[i][j] * 2 + (i < m ? g1[i] : 0));
                    ans = max(ans, f[i][j] * 2 + (j < n ? g2[j] : 0));
                }
            }
        }
        return ans;
    }

private:
    void expand(const string& s, vector<int>& g, int l, int r) {
        while (l >= 0 && r < s.size() && s[l] == s[r]) {
            g[l] = max(g[l], r - l + 1);
            --l;
            ++r;
        }
    }

    vector<int> calc(const string& s) {
        int n = s.size();
        vector<int> g(n, 0);
        for (int i = 0; i < n; ++i) {
            expand(s, g, i, i);
            expand(s, g, i, i + 1);
        }
        return g;
    }
};

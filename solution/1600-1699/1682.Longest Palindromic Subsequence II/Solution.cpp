class Solution {
public:
    int f[251][251][27];

    int longestPalindromeSubseq(string s) {
        int n = s.size();
        memset(f, -1, sizeof f);
        function<int(int, int, int)> dfs = [&](int i, int j, int x) -> int {
            if (i >= j) return 0;
            if (f[i][j][x] != -1) return f[i][j][x];
            int ans = 0;
            if (s[i] == s[j] && s[i] - 'a' != x)
                ans = dfs(i + 1, j - 1, s[i] - 'a') + 2;
            else
                ans = max(dfs(i + 1, j, x), dfs(i, j - 1, x));
            f[i][j][x] = ans;
            return ans;
        };
        return dfs(0, n - 1, 26);
    }
};
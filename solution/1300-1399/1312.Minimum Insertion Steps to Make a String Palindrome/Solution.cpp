class Solution {
public:
    int minInsertions(string s) {
        int n = s.size();
        int f[n][n];
        memset(f, -1, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i >= j) {
                return 0;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            int ans = 1 << 30;
            if (s[i] == s[j]) {
                ans = dfs(i + 1, j - 1);
            } else {
                ans = min(dfs(i + 1, j), dfs(i, j - 1)) + 1;
            }
            return f[i][j] = ans;
        };
        return dfs(0, n - 1);
    }
};
class Solution {
public:
    bool isScramble(string s1, string s2) {
        int n = s1.size();
        int f[n][n][n + 1];
        memset(f, -1, sizeof(f));
        function<bool(int, int, int)> dfs = [&](int i, int j, int k) -> int {
            if (f[i][j][k] != -1) {
                return f[i][j][k] == 1;
            }
            if (k == 1) {
                return s1[i] == s2[j];
            }
            for (int h = 1; h < k; ++h) {
                if (dfs(i, j, h) && dfs(i + h, j + h, k - h)) {
                    return f[i][j][k] = true;
                }
                if (dfs(i + h, j, k - h) && dfs(i, j + k - h, h)) {
                    return f[i][j][k] = true;
                }
            }
            return f[i][j][k] = false;
        };
        return dfs(0, 0, n);
    }
};
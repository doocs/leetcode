class Solution {
public:
    int countVowelStrings(int n) {
        int f[n][5];
        memset(f, 0, sizeof f);
        function<int(int, int)> dfs = [&](int i, int j) {
            if (i >= n) {
                return 1;
            }
            if (f[i][j]) {
                return f[i][j];
            }
            int ans = 0;
            for (int k = j; k < 5; ++k) {
                ans += dfs(i + 1, k);
            }
            return f[i][j] = ans;
        };
        return dfs(0, 0);
    }
};
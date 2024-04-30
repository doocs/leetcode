class Solution {
public:
    int stoneGameVII(vector<int>& stones) {
        int n = stones.size();
        int s[n + 1];
        memset(s, 0, sizeof(s));
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + stones[i];
        }
        int f[n][n];
        memset(f, 0, sizeof(f));
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                int a = s[j + 1] - s[i + 1] - f[i + 1][j];
                int b = s[j] - s[i] - f[i][j - 1];
                f[i][j] = max(a, b);
            }
        }
        return f[0][n - 1];
    }
};
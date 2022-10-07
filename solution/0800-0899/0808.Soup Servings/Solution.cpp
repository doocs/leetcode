class Solution {
public:
    double f[200][200];

    double soupServings(int n) {
        memset(f, 0, sizeof f);
        return n > 4800 ? 1 : dfs((n + 24) / 25, (n + 24) / 25);
    }

    double dfs(int i, int j) {
        if (i <= 0 && j <= 0) return 0.5;
        if (i <= 0) return 1;
        if (j <= 0) return 0;
        if (f[i][j] > 0) return f[i][j];
        double ans = 0.25 * (dfs(i - 4, j) + dfs(i - 3, j - 1) + dfs(i - 2, j - 2) + dfs(i - 1, j - 3));
        f[i][j] = ans;
        return ans;
    }
};
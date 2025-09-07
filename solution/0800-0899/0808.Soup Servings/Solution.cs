public class Solution {
    private double[,] f = new double[200, 200];

    public double SoupServings(int n) {
        if (n > 4800) {
            return 1.0;
        }

        return Dfs((n + 24) / 25, (n + 24) / 25);
    }

    private double Dfs(int i, int j) {
        if (i <= 0 && j <= 0) {
            return 0.5;
        }
        if (i <= 0) {
            return 1.0;
        }
        if (j <= 0) {
            return 0.0;
        }
        if (f[i, j] > 0) {
            return f[i, j];
        }

        double ans = 0.25 * (Dfs(i - 4, j) + Dfs(i - 3, j - 1) + Dfs(i - 2, j - 2) + Dfs(i - 1, j - 3));
        f[i, j] = ans;
        return ans;
    }
}

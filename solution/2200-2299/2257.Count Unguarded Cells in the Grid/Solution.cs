public class Solution {
    public int CountUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[,] g = new int[m, n];
        foreach (var e in guards) {
            g[e[0], e[1]] = 2;
        }
        foreach (var e in walls) {
            g[e[0], e[1]] = 2;
        }
        int[] dirs = { -1, 0, 1, 0, -1 };
        foreach (var e in guards) {
            int x0 = e[0], y0 = e[1];
            for (int k = 0; k < 4; ++k) {
                int x = x0, y = y0;
                int a = dirs[k], b = dirs[k + 1];
                while (x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && g[x + a, y + b] < 2) {
                    x += a;
                    y += b;
                    g[x, y] = 1;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (g[i, j] == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}

public class Solution {
    public int MatrixScore(int[][] grid) {
        int m = grid.Length, n = grid[0].Length;
        for (int i = 0; i < m; ++i) {
            if (grid[i][0] == 0) {
                for (int j = 0; j < n; ++j) {
                    grid[i][j] ^= 1;
                }
            }
        }
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            int cnt = 0;
            for (int i = 0; i < m; ++i) {
                if (grid[i][j] == 1) {
                    ++cnt;
                }
            }
            ans += Math.Max(cnt, m - cnt) * (1 << (n - j - 1));
        }
        return ans;
    }
}

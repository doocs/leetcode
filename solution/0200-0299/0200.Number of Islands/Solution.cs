public class Solution {
    public int NumIslands(char[][] grid) {
        int m = grid.Length;
        int n = grid[0].Length;
        int ans = 0;
        int[] dirs = { -1, 0, 1, 0, -1 };

        void Dfs(int i, int j) {
            grid[i][j] = '0';
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];
                if (x >= 0 && x < m &&
                    y >= 0 && y < n &&
                    grid[x][y] == '1')
                {
                    Dfs(x, y);
                }
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    Dfs(i, j);
                    ans++;
                }
            }
        }

        return ans;
    }
}

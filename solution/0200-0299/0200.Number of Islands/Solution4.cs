public class Solution {
    public int NumIslands(char[][] grid) {
        int m = grid.Length;
        int n = grid[0].Length;
        int ans = 0;
        int[] dirs = { -1, 0, 1, 0, -1 };

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] != '1') {
                    continue;
                }
                grid[i][j] = '0';
                var stk = new Stack<(int, int)>();
                stk.Push((i, j));
                while (stk.Count > 0) {
                    var (a, b) = stk.Pop();
                    for (int k = 0; k < 4; ++k) {
                        int x = a + dirs[k];
                        int y = b + dirs[k + 1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                            grid[x][y] = '0';
                            stk.Push((x, y));
                        }
                    }
                }
                ans++;
            }
        }

        return ans;
    }
}

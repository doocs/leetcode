public class Solution {
    public int[][] SortMatrix(int[][] grid) {
        int n = grid.Length;
        for (int k = n - 2; k >= 0; --k) {
            int i = k, j = 0;
            List<int> t = new List<int>();
            while (i < n && j < n) {
                t.Add(grid[i++][j++]);
            }
            t.Sort();
            foreach (int x in t) {
                grid[--i][--j] = x;
            }
        }
        for (int k = n - 2; k > 0; --k) {
            int i = k, j = n - 1;
            List<int> t = new List<int>();
            while (i >= 0 && j >= 0) {
                t.Add(grid[i--][j--]);
            }
            t.Sort();
            foreach (int x in t) {
                grid[++i][++j] = x;
            }
        }
        return grid;
    }
}

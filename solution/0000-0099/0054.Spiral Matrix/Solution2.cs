public class Solution {
    public IList<int> SpiralOrder(int[][] matrix) {
        int m = matrix.Length, n = matrix[0].Length;
        int[] dirs = { 0, 1, 0, -1, 0 };
        int i = 0, j = 0, k = 0;
        IList<int> ans = new List<int>();
        for (int h = m * n; h > 0; --h) {
            ans.Add(matrix[i][j]);
            matrix[i][j] += 300;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] > 100) {
                k = (k + 1) % 4;
            }
            i += dirs[k];
            j += dirs[k + 1];
        }
        for (int a = 0; a < m; ++a) {
            for (int b = 0; b < n; ++b) {
                matrix[a][b] -= 300;
            }
        }
        return ans;
    }
}

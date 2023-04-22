public class Solution {
    public IList<int> SpiralOrder(int[][] matrix) {
        int m = matrix.Length, n = matrix[0].Length;
        int[] dirs = new int[] {0, 1, 0, -1, 0};
        IList<int> ans = new List<int>();
        bool[,] visited = new bool[m, n];
        for (int h = m * n, i = 0, j = 0, k = 0; h > 0; --h) {
            ans.Add(matrix[i][j]);
            visited[i, j] = true;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < 0 || x >= m || y < 0 || y >= n || visited[x, y]) {
                k = (k + 1) % 4;
            }
            i += dirs[k];
            j += dirs[k + 1];
        }
        return ans;
    }
}
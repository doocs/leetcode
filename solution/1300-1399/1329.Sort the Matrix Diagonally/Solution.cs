public class Solution {
    public int[][] DiagonalSort(int[][] mat) {
        int m = mat.Length;
        int n = mat[0].Length;
        List<List<int>> g = new List<List<int>>();
        for (int i = 0; i < m + n; i++) {
            g.Add(new List<int>());
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                g[m - i + j].Add(mat[i][j]);
            }
        }
        foreach (var e in g) {
            e.Sort((a, b) => b.CompareTo(a));
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = g[m - i + j][g[m - i + j].Count - 1];
                g[m - i + j].RemoveAt(g[m - i + j].Count - 1);
                mat[i][j] = val;
            }
        }
        return mat;
    }
}
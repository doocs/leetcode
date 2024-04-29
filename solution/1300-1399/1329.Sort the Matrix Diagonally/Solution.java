class Solution {
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        List<Integer>[] g = new List[m + n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                g[m - i + j].add(mat[i][j]);
            }
        }
        for (var e : g) {
            Collections.sort(e, (a, b) -> b - a);
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                mat[i][j] = g[m - i + j].removeLast();
            }
        }
        return mat;
    }
}
class Solution {
    public int maxIncreasingCells(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        TreeMap<Integer, List<int[]>> g = new TreeMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                g.computeIfAbsent(mat[i][j], k -> new ArrayList<>()).add(new int[] {i, j});
            }
        }
        int[] rowMax = new int[m];
        int[] colMax = new int[n];
        int ans = 0;
        for (var e : g.entrySet()) {
            var pos = e.getValue();
            int[] mx = new int[pos.size()];
            int k = 0;
            for (var p : pos) {
                int i = p[0], j = p[1];
                mx[k] = Math.max(rowMax[i], colMax[j]) + 1;
                ans = Math.max(ans, mx[k++]);
            }
            for (k = 0; k < mx.length; ++k) {
                int i = pos.get(k)[0], j = pos.get(k)[1];
                rowMax[i] = Math.max(rowMax[i], mx[k]);
                colMax[j] = Math.max(colMax[j], mx[k]);
            }
        }
        return ans;
    }
}
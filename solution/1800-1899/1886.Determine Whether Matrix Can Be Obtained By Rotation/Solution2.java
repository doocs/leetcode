class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        for (int k = 0; k < 4; ++k) {
            int[][] g = new int[n][n];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    g[i][j] = mat[j][n - i - 1];
                }
            }
            if (equals(g, target)) {
                return true;
            }
            mat = g;
        }
        return false;
    }

    private boolean equals(int[][] a, int[][] b) {
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
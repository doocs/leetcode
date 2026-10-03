class Solution {
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int n = floor.length();
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + (floor.charAt(i) == '1' ? 1 : 0);
        }
        int[][] f = new int[n + 1][numCarpets + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j <= numCarpets; ++j) {
                if (floor.charAt(i) == '0') {
                    f[i][j] = f[i + 1][j];
                } else if (j == 0) {
                    f[i][j] = s[n] - s[i];
                } else {
                    int cover = i + carpetLen <= n ? f[i + carpetLen][j - 1] : 0;
                    f[i][j] = Math.min(1 + f[i + 1][j], cover);
                }
            }
        }
        return f[0][numCarpets];
    }
}

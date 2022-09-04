class Solution {
    private int ans;
    public int maximumRows(int[][] mat, int cols) {
        int m = mat.length, n = mat[0].length;
        int[] arr = new int[m];
        for (int i = 0; i < m; ++i) {
            int x = 0;
            for (int j = 0; j < n; ++j) {
                x |= mat[i][j] << j;
            }
            arr[i] = x;
        }
        int ans = 0;
        for (int mask = 1; mask <= 1 << n; ++mask) {
            if (Integer.bitCount(mask) > cols) {
                continue;
            }
            int t = 0;
            for (int v : arr) {
                if ((v & mask) == v) {
                    ++t;
                }
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
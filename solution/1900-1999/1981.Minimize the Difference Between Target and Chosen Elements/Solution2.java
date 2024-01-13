class Solution {
    public int minimizeTheDifference(int[][] mat, int target) {
        boolean[] f = {true};
        for (var row : mat) {
            int mx = 0;
            for (int x : row) {
                mx = Math.max(mx, x);
            }
            boolean[] g = new boolean[f.length + mx];
            for (int x : row) {
                for (int j = x; j < f.length + x; ++j) {
                    g[j] |= f[j - x];
                }
            }
            f = g;
        }
        int ans = 1 << 30;
        for (int j = 0; j < f.length; ++j) {
            if (f[j]) {
                ans = Math.min(ans, Math.abs(j - target));
            }
        }
        return ans;
    }
}
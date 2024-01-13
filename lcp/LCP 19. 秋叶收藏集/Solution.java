class Solution {
    public int minimumOperations(String leaves) {
        int n = leaves.length();
        final int inf = 1 << 30;
        var f = new int[n][3];
        for (var g : f) {
            Arrays.fill(g, inf);
        }
        f[0][0] = leaves.charAt(0) == 'r' ? 0 : 1;
        for (int i = 1; i < n; ++i) {
            if (leaves.charAt(i) == 'r') {
                f[i][0] = f[i - 1][0];
                f[i][1] = Math.min(f[i - 1][0], f[i - 1][1]) + 1;
                f[i][2] = Math.min(f[i - 1][2], f[i - 1][1]);
            } else {
                f[i][0] = f[i - 1][0] + 1;
                f[i][1] = Math.min(f[i - 1][0], f[i - 1][1]);
                f[i][2] = Math.min(f[i - 1][2], f[i - 1][1]) + 1;
            }
        }
        return f[n - 1][2];
    }
}
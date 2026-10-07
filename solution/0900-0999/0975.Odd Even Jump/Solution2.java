class Solution {
    public int oddEvenJumps(int[] arr) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int n = arr.length;
        int[][] g = new int[n][2];
        for (int i = n - 1; i >= 0; --i) {
            var hi = tm.ceilingEntry(arr[i]);
            g[i][1] = hi == null ? -1 : hi.getValue();
            var lo = tm.floorEntry(arr[i]);
            g[i][0] = lo == null ? -1 : lo.getValue();
            tm.put(arr[i], i);
        }
        boolean[][] f = new boolean[n][2];
        f[n - 1][0] = f[n - 1][1] = true;
        for (int i = n - 2; i >= 0; --i) {
            for (int k = 0; k < 2; ++k) {
                int j = g[i][k];
                if (j != -1) {
                    f[i][k] = f[j][k ^ 1];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (f[i][1]) {
                ++ans;
            }
        }
        return ans;
    }
}

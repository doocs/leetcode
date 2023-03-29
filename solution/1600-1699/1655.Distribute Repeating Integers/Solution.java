class Solution {
    public boolean canDistribute(int[] nums, int[] quantity) {
        int m = quantity.length;
        int[] s = new int[1 << m];
        for (int i = 1; i < 1 << m; ++i) {
            for (int j = 0; j < m; ++j) {
                if ((i >> j & 1) != 0) {
                    s[i] = s[i ^ (1 << j)] + quantity[j];
                    break;
                }
            }
        }
        Map<Integer, Integer> cnt = new HashMap<>(50);
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        int n = cnt.size();
        int[] arr = new int[n];
        int i = 0;
        for (int x : cnt.values()) {
            arr[i++] = x;
        }
        boolean[][] f = new boolean[n][1 << m];
        for (i = 0; i < n; ++i) {
            f[i][0] = true;
        }
        for (i = 0; i < n; ++i) {
            for (int j = 1; j < 1 << m; ++j) {
                if (i > 0 && f[i - 1][j]) {
                    f[i][j] = true;
                    continue;
                }
                for (int k = j; k > 0; k = (k - 1) & j) {
                    boolean ok1 = i == 0 ? j == k : f[i - 1][j ^ k];
                    boolean ok2 = s[k] <= arr[i];
                    if (ok1 && ok2) {
                        f[i][j] = true;
                        break;
                    }
                }
            }
        }
        return f[n - 1][(1 << m) - 1];
    }
}
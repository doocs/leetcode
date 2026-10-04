class Solution {
    public int minimumPartition(String s, int k) {
        int n = s.length();
        int inf = 1 << 30;
        int[] f = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            f[i] = inf;
        }
        for (int i = n - 1; i >= 0; --i) {
            long v = 0;
            for (int j = i; j < n; ++j) {
                v = v * 10 + (s.charAt(j) - '0');
                if (v > k) {
                    break;
                }
                f[i] = Math.min(f[i], f[j + 1]);
            }
            ++f[i];
        }
        return f[0] < inf ? f[0] : -1;
    }
}

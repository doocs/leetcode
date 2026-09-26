class Solution {
    public int numberOfArrays(String s, int k) {
        final int mod = 1_000_000_007;
        int n = s.length();
        int[] f = new int[n + 1];
        f[n] = 1;
        for (int i = n - 1; i >= 0; --i) {
            if (s.charAt(i) == '0') {
                continue;
            }
            long x = 0;
            for (int j = i; j < n; ++j) {
                x = x * 10 + s.charAt(j) - '0';
                if (x > k) {
                    break;
                }
                f[i] = (int) ((f[i] + f[j + 1]) % mod);
            }
        }
        return f[0];
    }
}

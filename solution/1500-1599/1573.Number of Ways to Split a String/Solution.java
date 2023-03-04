class Solution {
    private String s;

    public int numWays(String s) {
        this.s = s;
        int cnt = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '1') {
                ++cnt;
            }
        }
        int m = cnt % 3;
        if (m != 0) {
            return 0;
        }
        final int mod = (int) 1e9 + 7;
        if (cnt == 0) {
            return (int) (((n - 1L) * (n - 2) / 2) % mod);
        }
        cnt /= 3;
        long i1 = find(cnt), i2 = find(cnt + 1);
        long j1 = find(cnt * 2), j2 = find(cnt * 2 + 1);
        return (int) ((i2 - i1) * (j2 - j1) % mod);
    }

    private int find(int x) {
        int t = 0;
        for (int i = 0;; ++i) {
            t += s.charAt(i) == '1' ? 1 : 0;
            if (t == x) {
                return i;
            }
        }
    }
}
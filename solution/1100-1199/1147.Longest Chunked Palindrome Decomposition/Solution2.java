class Solution {
    private long[] h;
    private long[] p;

    public int longestDecomposition(String text) {
        int n = text.length();
        int base = 131;
        h = new long[n + 10];
        p = new long[n + 10];
        p[0] = 1;
        for (int i = 0; i < n; ++i) {
            int t = text.charAt(i) - 'a' + 1;
            h[i + 1] = h[i] * base + t;
            p[i + 1] = p[i] * base;
        }
        int ans = 0;
        for (int i = 0, j = n - 1; i <= j;) {
            boolean ok = false;
            for (int k = 1; i + k - 1 < j - k + 1; ++k) {
                if (get(i + 1, i + k) == get(j - k + 2, j + 1)) {
                    ans += 2;
                    i += k;
                    j -= k;
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                ++ans;
                break;
            }
        }
        return ans;
    }

    private long get(int i, int j) {
        return h[j] - h[i - 1] * p[j - i + 1];
    }
}
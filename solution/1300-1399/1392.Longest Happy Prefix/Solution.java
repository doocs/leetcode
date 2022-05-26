class Solution {
    private long[] p;
    private long[] h;

    public String longestPrefix(String s) {
        int base = 131;
        int n = s.length();
        p = new long[n + 10];
        h = new long[n + 10];
        p[0] = 1;
        for (int i = 0; i < n; ++i) {
            p[i + 1] = p[i] * base;
            h[i + 1] = h[i] * base + s.charAt(i);
        }
        for (int l = n - 1; l > 0; --l) {
            if (get(1, l) == get(n - l + 1, n)) {
                return s.substring(0, l);
            }
        }
        return "";
    }

    private long get(int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }
}
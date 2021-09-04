class Solution {
    private long[] p;
    private long[] h;

    public String longestPrefix(String s) {
        int base = 131;
        int n = 100010;
        p = new long[n];
        h = new long[n];
        p[0] = 1;
        for (int i = 1; i <= s.length(); ++i) {
            p[i] = p[i - 1] * base;
            h[i] = h[i - 1] * base + s.charAt(i - 1);
        }
        int l = s.length();
        for (int i = l - 1; i > 0; --i) {
            if (get(1, i) == get(l - i + 1, l)) {
                return s.substring(0, i);
            }
        }
        return "";
    }

    private long get(int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }
}
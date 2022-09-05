class Solution {
    private long[] h;
    private long[] p;

    public int distinctEchoSubstrings(String text) {
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
        Set<Long> vis = new HashSet<>();
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; j += 2) {
                int k = (i + j) >> 1;
                long a = get(i + 1, k + 1);
                long b = get(k + 2, j + 1);
                if (a == b) {
                    vis.add(a);
                }
            }
        }
        return vis.size();
    }

    private long get(int i, int j) {
        return h[j] - h[i - 1] * p[j - i + 1];
    }
}
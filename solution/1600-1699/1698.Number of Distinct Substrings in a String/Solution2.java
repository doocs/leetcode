class Solution {
    public int countDistinct(String s) {
        int base = 131;
        int n = s.length();
        long[] p = new long[n + 10];
        long[] h = new long[n + 10];
        p[0] = 1;
        for (int i = 0; i < n; ++i) {
            p[i + 1] = p[i] * base;
            h[i + 1] = h[i] * base + s.charAt(i);
        }
        Set<Long> ss = new HashSet<>();
        for (int i = 1; i <= n; ++i) {
            for (int j = i; j <= n; ++j) {
                long t = h[j] - h[i - 1] * p[j - i + 1];
                ss.add(t);
            }
        }
        return ss.size();
    }
}
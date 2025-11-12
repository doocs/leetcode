class Solution {
    public int distinctPoints(String s, int k) {
        int n = s.length();
        int[] f = new int[n + 1];
        int[] g = new int[n + 1];
        int x = 0, y = 0;
        for (int i = 1; i <= n; ++i) {
            char c = s.charAt(i - 1);
            if (c == 'U') {
                ++y;
            } else if (c == 'D') {
                --y;
            } else if (c == 'L') {
                --x;
            } else {
                ++x;
            }
            f[i] = x;
            g[i] = y;
        }
        Set<Long> st = new HashSet<>();
        for (int i = k; i <= n; ++i) {
            int a = f[n] - (f[i] - f[i - k]);
            int b = g[n] - (g[i] - g[i - k]);
            st.add(1L * a * n + b);
        }
        return st.size();
    }
}

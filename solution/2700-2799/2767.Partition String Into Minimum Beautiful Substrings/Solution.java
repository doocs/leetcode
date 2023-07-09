class Solution {
    private Integer[] f;
    private String s;
    private Set<Long> ss = new HashSet<>();
    private int n;

    public int minimumBeautifulSubstrings(String s) {
        n = s.length();
        this.s = s;
        f = new Integer[n];
        long x = 1;
        for (int i = 0; i <= n; ++i) {
            ss.add(x);
            x *= 5;
        }
        int ans = dfs(0);
        return ans > n ? -1 : ans;
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (s.charAt(i) == '0') {
            return n + 1;
        }
        if (f[i] != null) {
            return f[i];
        }
        long x = 0;
        int ans = n + 1;
        for (int j = i; j < n; ++j) {
            x = x << 1 | (s.charAt(j) - '0');
            if (ss.contains(x)) {
                ans = Math.min(ans, 1 + dfs(j + 1));
            }
        }
        return f[i] = ans;
    }
}
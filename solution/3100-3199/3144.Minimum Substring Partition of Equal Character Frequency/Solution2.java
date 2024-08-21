class Solution {
    private int n;
    private char[] s;
    private Integer[] f;

    public int minimumSubstringsInPartition(String s) {
        n = s.length();
        f = new Integer[n];
        this.s = s.toCharArray();
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int[] cnt = new int[26];
        int ans = n - i;
        int k = 0, m = 0;
        for (int j = i; j < n; ++j) {
            k += ++cnt[s[j] - 'a'] == 1 ? 1 : 0;
            m = Math.max(m, cnt[s[j] - 'a']);
            if (j - i + 1 == k * m) {
                ans = Math.min(ans, 1 + dfs(j + 1));
            }
        }
        return f[i] = ans;
    }
}

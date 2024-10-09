class Solution {
    private int n;
    private char[] s;
    private Integer[][] f;
    private final int mod = (int) 1e9 + 7;

    public int numberOfWays(String corridor) {
        s = corridor.toCharArray();
        n = s.length;
        f = new Integer[n][3];
        return dfs(0, 0);
    }

    private int dfs(int i, int k) {
        if (i >= n) {
            return k == 2 ? 1 : 0;
        }
        if (f[i][k] != null) {
            return f[i][k];
        }
        k += s[i] == 'S' ? 1 : 0;
        if (k > 2) {
            return 0;
        }
        int ans = dfs(i + 1, k);
        if (k == 2) {
            ans = (ans + dfs(i + 1, 0)) % mod;
        }
        return f[i][k] = ans;
    }
}

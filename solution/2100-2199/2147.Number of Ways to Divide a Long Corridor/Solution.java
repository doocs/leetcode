class Solution {
    private String s;
    private int n;
    private int[][] f;
    private static final int MOD = (int) 1e9 + 7;

    public int numberOfWays(String corridor) {
        s = corridor;
        n = s.length();
        f = new int[n][3];
        for (var e : f) {
            Arrays.fill(e, -1);
        }
        return dfs(0, 0);
    }

    private int dfs(int i, int cnt) {
        if (i == n) {
            return cnt == 2 ? 1 : 0;
        }
        cnt += s.charAt(i) == 'S' ? 1 : 0;
        if (cnt > 2) {
            return 0;
        }
        if (f[i][cnt] != -1) {
            return f[i][cnt];
        }
        int ans = dfs(i + 1, cnt);
        if (cnt == 2) {
            ans += dfs(i + 1, 0);
            ans %= MOD;
        }
        f[i][cnt] = ans;
        return ans;
    }
}
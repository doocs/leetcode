class Solution {
    private final int[] cnt = new int[10];
    private final int mod = (int) 1e9 + 7;
    private Integer[][][][] f;
    private long[][] c;

    public int countBalancedPermutations(String num) {
        int s = 0;
        for (char c : num.toCharArray()) {
            cnt[c - '0']++;
            s += c - '0';
        }
        if (s % 2 == 1) {
            return 0;
        }
        int n = num.length();
        int m = n / 2 + 1;
        f = new Integer[10][s / 2 + 1][m][m + 1];
        c = new long[m + 1][m + 1];
        c[0][0] = 1;
        for (int i = 1; i <= m; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;
            }
        }
        return dfs(0, s / 2, n / 2, (n + 1) / 2);
    }

    private int dfs(int i, int j, int a, int b) {
        if (i > 9) {
            return ((j | a | b) == 0) ? 1 : 0;
        }
        if (a == 0 && j != 0) {
            return 0;
        }
        if (f[i][j][a][b] != null) {
            return f[i][j][a][b];
        }
        int ans = 0;
        for (int l = 0; l <= Math.min(cnt[i], a); ++l) {
            int r = cnt[i] - l;
            if (r >= 0 && r <= b && l * i <= j) {
                int t = (int) (c[a][l] * c[b][r] % mod * dfs(i + 1, j - l * i, a - l, b - r) % mod);
                ans = (ans + t) % mod;
            }
        }
        return f[i][j][a][b] = ans;
    }
}

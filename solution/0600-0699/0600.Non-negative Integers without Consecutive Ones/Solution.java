class Solution {
    private char[] s;
    private Integer[][] f;

    public int findIntegers(int n) {
        s = Integer.toBinaryString(n).toCharArray();
        f = new Integer[s.length][2];
        return dfs(0, 0, true);
    }

    private int dfs(int pos, int pre, boolean limit) {
        if (pos >= s.length) {
            return 1;
        }
        if (!limit && f[pos][pre] != null) {
            return f[pos][pre];
        }
        int up = limit ? s[pos] - '0' : 1;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            if (!(pre == 1 && i == 1)) {
                ans += dfs(pos + 1, i, limit && i == up);
            }
        }
        if (!limit) {
            f[pos][pre] = ans;
        }
        return ans;
    }
}

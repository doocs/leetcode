class Solution {
    private char[] num;
    private Long[][] f;
    private final int base = 90;

    public long countBalanced(long low, long high) {
        if (high < 11) {
            return 0;
        }
        low = Math.max(low, 11);
        num = String.valueOf(low - 1).toCharArray();
        f = new Long[num.length][base << 1 | 1];
        long a = dfs(0, 0, true);
        num = String.valueOf(high).toCharArray();
        f = new Long[num.length][base << 1 | 1];
        long b = dfs(0, 0, true);
        return b - a;
    }

    private long dfs(int pos, int diff, boolean lim) {
        if (pos >= num.length) {
            return diff == 0 ? 1 : 0;
        }
        if (!lim && f[pos][diff + base] != null) {
            return f[pos][diff + base];
        }
        int up = lim ? num[pos] - '0' : 9;
        long res = 0;
        for (int i = 0; i <= up; ++i) {
            res += dfs(pos + 1, diff + i * (pos % 2 == 0 ? 1 : -1), lim && i == up);
        }
        if (!lim) {
            f[pos][diff + base] = res;
        }
        return res;
    }
}

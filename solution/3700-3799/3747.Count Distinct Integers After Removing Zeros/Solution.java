class Solution {
    private char[] s;
    private Long[][][][] f;

    public long countDistinct(long n) {
        s = String.valueOf(n).toCharArray();
        f = new Long[s.length][2][2][2];
        return dfs(0, 0, 1, 1);
    }

    private long dfs(int i, int zero, int lead, int limit) {
        if (i == s.length) {
            return (zero == 0 && lead == 0) ? 1 : 0;
        }

        if (limit == 0 && f[i][zero][lead][limit] != null) {
            return f[i][zero][lead][limit];
        }

        int up = limit == 1 ? s[i] - '0' : 9;
        long ans = 0;
        for (int d = 0; d <= up; d++) {
            int nxtZero = zero == 1 || (d == 0 && lead == 0) ? 1 : 0;
            int nxtLead = (lead == 1 && d == 0) ? 1 : 0;
            int nxtLimit = (limit == 1 && d == up) ? 1 : 0;
            ans += dfs(i + 1, nxtZero, nxtLead, nxtLimit);
        }

        if (limit == 0) {
            f[i][zero][lead][limit] = ans;
        }
        return ans;
    }
}

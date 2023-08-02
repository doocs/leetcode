import java.math.BigInteger;

class Solution {
    private final int mod = (int) 1e9 + 7;
    private String num;
    private Integer[][] f;

    public int countSteppingNumbers(String low, String high) {
        f = new Integer[high.length() + 1][10];
        num = high;
        int a = dfs(0, -1, true, true);
        f = new Integer[high.length() + 1][10];
        num = new BigInteger(low).subtract(BigInteger.ONE).toString();
        int b = dfs(0, -1, true, true);
        return (a - b + mod) % mod;
    }

    private int dfs(int pos, int pre, boolean lead, boolean limit) {
        if (pos >= num.length()) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && f[pos][pre] != null) {
            return f[pos][pre];
        }
        int ans = 0;
        int up = limit ? num.charAt(pos) - '0' : 9;
        for (int i = 0; i <= up; ++i) {
            if (i == 0 && lead) {
                ans += dfs(pos + 1, pre, true, limit && i == up);
            } else if (pre == -1 || Math.abs(pre - i) == 1) {
                ans += dfs(pos + 1, i, false, limit && i == up);
            }
            ans %= mod;
        }
        if (!lead && !limit) {
            f[pos][pre] = ans;
        }
        return ans;
    }
}
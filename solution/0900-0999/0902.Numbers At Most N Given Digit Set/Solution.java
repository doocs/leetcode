class Solution {
    private int[] a = new int[12];
    private int[][] dp = new int[12][2];
    private Set<Integer> s = new HashSet<>();

    public int atMostNGivenDigitSet(String[] digits, int n) {
        for (var e : dp) {
            Arrays.fill(e, -1);
        }
        for (String d : digits) {
            s.add(Integer.parseInt(d));
        }
        int len = 0;
        while (n > 0) {
            a[++len] = n % 10;
            n /= 10;
        }
        return dfs(len, 1, true);
    }

    private int dfs(int pos, int lead, boolean limit) {
        if (pos <= 0) {
            return lead ^ 1;
        }
        if (!limit && lead != 1 && dp[pos][lead] != -1) {
            return dp[pos][lead];
        }
        int ans = 0;
        int up = limit ? a[pos] : 9;
        for (int i = 0; i <= up; ++i) {
            if (i == 0 && lead == 1) {
                ans += dfs(pos - 1, lead, limit && i == up);
            } else if (s.contains(i)) {
                ans += dfs(pos - 1, 0, limit && i == up);
            }
        }
        if (!limit && lead == 0) {
            dp[pos][lead] = ans;
        }
        return ans;
    }
}
class Solution {
    private int n;
    private int[][] statements;

    public int maximumGood(int[][] statements) {
        n = statements.length;
        this.statements = statements;
        int ans = 0;
        for (int k = 0; k < (1 << n); ++k) {
            ans = Math.max(ans, check(k));
        }
        return ans;
    }

    private int check(int k) {
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (((k >> i) & 1) == 1) {
                for (int j = 0; j < n; ++j) {
                    if (statements[i][j] < 2 && ((k >> j) & 1) != statements[i][j]) {
                        return 0;
                    }
                }
                ++cnt;
            }
        }
        return cnt;
    }
}
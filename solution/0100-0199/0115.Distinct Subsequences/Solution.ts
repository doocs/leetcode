function numDistinct(s: string, t: string): number {
    const m = s.length;
    const n = t.length;
    const dp: number[][] = new Array(m + 1)
        .fill(0)
        .map(() => new Array(n + 1).fill(0));
    for (let i = 0; i <= m; ++i) {
        dp[i][0] = 1;
    }
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            dp[i][j] = dp[i - 1][j];
            if (s.charAt(i - 1) === t.charAt(j - 1)) {
                dp[i][j] += dp[i - 1][j - 1];
            }
        }
    }
    return dp[m][n];
}

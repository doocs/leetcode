function mincostTickets(days: number[], costs: number[]): number {
    const n = days.length,
        m = days[n - 1] + 1;
    const [a, b, c] = costs;
    let dp = new Array(m).fill(0);
    for (let i = 1; i < m; i++) {
        let x = days.includes(i) ? dp[i - 1] + a : dp[i - 1];
        let y = (i > 7 ? dp[i - 7] : dp[0]) + b;
        let z = (i > 30 ? dp[i - 30] : dp[0]) + c;
        dp[i] = Math.min(x, y, z);
    }
    return dp[m - 1];
}

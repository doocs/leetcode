function numSquares(n: number): number {
    let dp = new Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        let min = Infinity;
        for (let j = 1; j * j <= i; ++j) {
            min = Math.min(min, dp[i - j * j]);
        }
        dp[i] = min + 1;
    }
    return dp.pop();
}

function new21Game(n: number, k: number, maxPts: number): number {
    if (!k) return 1.0;
    let dp = new Array(k + maxPts).fill(0.0);
    for (let i = k; i <= n && i < k + maxPts; i++) {
        dp[i] = 1.0;
    }
    dp[k - 1] = (1.0 * Math.min(n - k + 1, maxPts)) / maxPts;
    for (let i = k - 2; i >= 0; i--) {
        dp[i] = dp[i + 1] - (dp[i + maxPts + 1] - dp[i + 1]) / maxPts;
    }
    return dp[0];
}

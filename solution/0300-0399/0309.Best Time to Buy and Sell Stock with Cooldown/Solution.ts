function maxProfit(prices: number[]): number {
    const n = prices.length;
    let dp = Array.from({ length: n }, v => new Array(3).fill(0));
    dp[0] = [0, -prices[0], Number.MIN_SAFE_INTEGER];
    for (let i = 1; i < n; i++) {
        dp[i] = [
            Math.max(dp[i - 1][0], dp[i - 1][2]),
            Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]),
            dp[i - 1][1] + prices[i],
        ];
    }
    return Math.max(dp[n - 1][0], dp[n - 1][2]);
}

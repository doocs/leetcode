function maxProfit(prices: number[]): number {
    const n = prices.length;
    const f: number[][] = Array.from({ length: n }, () => Array.from({ length: 2 }, () => 0));
    f[0][1] = -prices[0];
    for (let i = 1; i < n; ++i) {
        f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i]);
        f[i][1] = Math.max(f[i - 1][1], (i > 1 ? f[i - 2][0] : 0) - prices[i]);
    }
    return f[n - 1][0];
}

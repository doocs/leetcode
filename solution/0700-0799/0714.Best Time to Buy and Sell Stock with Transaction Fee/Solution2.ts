function maxProfit(prices: number[], fee: number): number {
    const n = prices.length;
    const f: number[][] = Array.from({ length: n }, () => [0, 0]);
    f[0][1] = -prices[0];
    for (let i = 1; i < n; ++i) {
        f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i] - fee);
        f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] - prices[i]);
    }
    return f[n - 1][0];
}

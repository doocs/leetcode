function maximumProfit(prices: number[], k: number): number {
    const n = prices.length;
    const f: number[][][] = Array.from({ length: n }, () =>
        Array.from({ length: k + 1 }, () => Array(3).fill(0)),
    );

    for (let j = 1; j <= k; ++j) {
        f[0][j][1] = -prices[0];
        f[0][j][2] = prices[0];
    }

    for (let i = 1; i < n; ++i) {
        for (let j = 1; j <= k; ++j) {
            f[i][j][0] = Math.max(
                f[i - 1][j][0],
                f[i - 1][j][1] + prices[i],
                f[i - 1][j][2] - prices[i],
            );
            f[i][j][1] = Math.max(f[i - 1][j][1], f[i - 1][j - 1][0] - prices[i]);
            f[i][j][2] = Math.max(f[i - 1][j][2], f[i - 1][j - 1][0] + prices[i]);
        }
    }

    return f[n - 1][k][0];
}

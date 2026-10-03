function maxProfit(prices: number[]): number {
    const n = prices.length;
    const f: number[][] = Array.from({ length: n + 2 }, () => Array(2).fill(0));
    for (let i = n - 1; i >= 0; --i) {
        for (let j = 0; j < 2; ++j) {
            let ans = f[i + 1][j];
            if (j) {
                ans = Math.max(ans, prices[i] + f[i + 2][0]);
            } else {
                ans = Math.max(ans, -prices[i] + f[i + 1][1]);
            }
            f[i][j] = ans;
        }
    }
    return f[0][0];
}

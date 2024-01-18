function maxProfit(k: number, prices: number[]): number {
    const n = prices.length;
    const f = Array.from({ length: n }, () =>
        Array.from({ length: k + 1 }, () => Array.from({ length: 2 }, () => -1)),
    );
    const dfs = (i: number, j: number, k: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i][j][k] !== -1) {
            return f[i][j][k];
        }
        let ans = dfs(i + 1, j, k);
        if (k) {
            ans = Math.max(ans, prices[i] + dfs(i + 1, j, 0));
        } else if (j) {
            ans = Math.max(ans, -prices[i] + dfs(i + 1, j - 1, 1));
        }
        return (f[i][j][k] = ans);
    };
    return dfs(0, k, 0);
}

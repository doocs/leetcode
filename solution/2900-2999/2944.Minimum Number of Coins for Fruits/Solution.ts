function minimumCoins(prices: number[]): number {
    const n = prices.length;
    const f: number[] = Array(n + 1).fill(0);
    const dfs = (i: number): number => {
        if (i > n) {
            return 0;
        }
        if (f[i] === 0) {
            f[i] = 1 << 30;
            for (let j = i + 1; j <= i * 2 + 1; ++j) {
                f[i] = Math.min(f[i], prices[i - 1] + dfs(j));
            }
        }
        return f[i];
    };
    return dfs(1);
}

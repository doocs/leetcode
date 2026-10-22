function numOfArrays(n: number, m: number, k: number): number {
    if (k === 0) {
        return 0;
    }
    const mod = 1e9 + 7;
    let dp: number[][] = Array.from({ length: k + 1 }, () => Array(m + 1).fill(0));
    for (let j = 1; j <= m; ++j) {
        dp[1][j] = 1;
    }
    for (let i = 2; i <= n; ++i) {
        const next: number[][] = Array.from({ length: k + 1 }, () => Array(m + 1).fill(0));
        for (let c = 1; c <= Math.min(i, k); ++c) {
            let prefix = 0;
            for (let j = 1; j <= m; ++j) {
                prefix = (prefix + dp[c - 1][j - 1]) % mod;
                next[c][j] = (dp[c][j] * j + prefix) % mod;
            }
        }
        dp = next;
    }
    return dp[k].reduce((sum, count) => (sum + count) % mod, 0);
}

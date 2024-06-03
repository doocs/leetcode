function getMoneyAmount(n: number): number {
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(n + 1).fill(0));
    for (let i = n - 1; i; --i) {
        for (let j = i + 1; j <= n; ++j) {
            f[i][j] = j + f[i][j - 1];
            for (let k = i; k < j; ++k) {
                f[i][j] = Math.min(f[i][j], k + Math.max(f[i][k - 1], f[k + 1][j]));
            }
        }
    }
    return f[1][n];
}

function numSquares(n: number): number {
    const m = Math.floor(Math.sqrt(n));
    const f: number[][] = Array(m + 1)
        .fill(0)
        .map(() => Array(n + 1).fill(1 << 30));
    f[0][0] = 0;
    for (let i = 1; i <= m; ++i) {
        for (let j = 0; j <= n; ++j) {
            f[i][j] = f[i - 1][j];
            if (j >= i * i) {
                f[i][j] = Math.min(f[i][j], f[i][j - i * i] + 1);
            }
        }
    }
    return f[m][n];
}

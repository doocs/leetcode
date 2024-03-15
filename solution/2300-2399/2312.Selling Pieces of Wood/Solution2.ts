function sellingWood(m: number, n: number, prices: number[][]): number {
    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    const d: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    for (const [h, w, p] of prices) {
        d[h][w] = p;
    }

    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            f[i][j] = d[i][j];
            for (let k = 1; k < i; k++) {
                f[i][j] = Math.max(f[i][j], f[k][j] + f[i - k][j]);
            }
            for (let k = 1; k < j; k++) {
                f[i][j] = Math.max(f[i][j], f[i][k] + f[i][j - k]);
            }
        }
    }

    return f[m][n];
}

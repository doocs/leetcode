function maxScore(a: number[], b: number[]): number {
    const m = a.length;
    const n = b.length;
    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    for (let i = 0; i < m; ++i) {
        f[i][n] = -Infinity;
    }
    for (let j = n - 1; j >= 0; --j) {
        for (let i = m - 1; i >= 0; --i) {
            f[i][j] = Math.max(f[i][j + 1], a[i] * b[j] + f[i + 1][j + 1]);
        }
    }
    return f[0][0];
}

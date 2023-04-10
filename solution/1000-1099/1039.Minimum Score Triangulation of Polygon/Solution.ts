function minScoreTriangulation(values: number[]): number {
    const n = values.length;
    const f: number[][] = Array.from({ length: n }, () =>
        Array.from({ length: n }, () => 0),
    );
    for (let l = 3; l <= n; ++l) {
        for (let i = 0; i + l - 1 < n; ++i) {
            const j = i + l - 1;
            f[i][j] = 1 << 30;
            for (let k = i + 1; k < j; ++k) {
                f[i][j] = Math.min(
                    f[i][j],
                    f[i][k] + f[k][j] + values[i] * values[k] * values[j],
                );
            }
        }
    }
    return f[0][n - 1];
}

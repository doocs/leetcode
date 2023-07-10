function minFallingPathSum(matrix: number[][]): number {
    const n = matrix.length;
    const f: number[] = new Array(n).fill(0);
    for (const row of matrix) {
        const g = f.slice();
        for (let j = 0; j < n; ++j) {
            if (j > 0) {
                g[j] = Math.min(g[j], f[j - 1]);
            }
            if (j + 1 < n) {
                g[j] = Math.min(g[j], f[j + 1]);
            }
            g[j] += row[j];
        }
        f.splice(0, n, ...g);
    }
    return Math.min(...f);
}

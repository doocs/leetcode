function mctFromLeafValues(arr: number[]): number {
    const n = arr.length;
    const f: number[][] = new Array(n).fill(0).map(() => new Array(n).fill(0));
    const g: number[][] = new Array(n).fill(0).map(() => new Array(n).fill(0));
    for (let i = n - 1; i >= 0; --i) {
        g[i][i] = arr[i];
        for (let j = i + 1; j < n; ++j) {
            g[i][j] = Math.max(g[i][j - 1], arr[j]);
            f[i][j] = 1 << 30;
            for (let k = i; k < j; ++k) {
                f[i][j] = Math.min(f[i][j], f[i][k] + f[k + 1][j] + g[i][k] * g[k + 1][j]);
            }
        }
    }
    return f[0][n - 1];
}

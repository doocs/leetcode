function mctFromLeafValues(arr: number[]): number {
    const n = arr.length;
    const f: number[][] = new Array(n).fill(0).map(() => new Array(n).fill(0));
    const g: number[][] = new Array(n).fill(0).map(() => new Array(n).fill(0));
    for (let i = n - 1; i >= 0; --i) {
        g[i][i] = arr[i];
        for (let j = i + 1; j < n; ++j) {
            g[i][j] = Math.max(g[i][j - 1], arr[j]);
        }
    }
    const dfs = (i: number, j: number): number => {
        if (i === j) {
            return 0;
        }
        if (f[i][j] > 0) {
            return f[i][j];
        }
        let ans = 1 << 30;
        for (let k = i; k < j; ++k) {
            ans = Math.min(ans, dfs(i, k) + dfs(k + 1, j) + g[i][k] * g[k + 1][j]);
        }
        return (f[i][j] = ans);
    };
    return dfs(0, n - 1);
}

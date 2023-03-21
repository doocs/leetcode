function numberOfWays(startPos: number, endPos: number, k: number): number {
    const mod = 10 ** 9 + 7;
    const f = new Array(k + 1).fill(0).map(() => new Array(k + 1).fill(-1));
    const dfs = (i: number, j: number): number => {
        if (i > j || j < 0) {
            return 0;
        }
        if (j === 0) {
            return i === 0 ? 1 : 0;
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        f[i][j] = dfs(i + 1, j - 1) + dfs(Math.abs(i - 1), j - 1);
        f[i][j] %= mod;
        return f[i][j];
    };
    return dfs(Math.abs(startPos - endPos), k);
}

function soupServings(n: number): number {
    const f = new Array(200).fill(0).map(() => new Array(200).fill(-1));
    const dfs = (i: number, j: number): number => {
        if (i <= 0 && j <= 0) {
            return 0.5;
        }
        if (i <= 0) {
            return 1;
        }
        if (j <= 0) {
            return 0;
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        f[i][j] =
            0.25 * (dfs(i - 4, j) + dfs(i - 3, j - 1) + dfs(i - 2, j - 2) + dfs(i - 1, j - 3));
        return f[i][j];
    };
    return n >= 4800 ? 1 : dfs(Math.ceil(n / 25), Math.ceil(n / 25));
}

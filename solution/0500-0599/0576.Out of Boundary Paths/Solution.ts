function findPaths(
    m: number,
    n: number,
    maxMove: number,
    startRow: number,
    startColumn: number,
): number {
    const f = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => Array(maxMove + 1).fill(-1)),
    );
    const mod = 1000000007;
    const dirs = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number, k: number): number => {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return k >= 0 ? 1 : 0;
        }
        if (k <= 0) {
            return 0;
        }
        if (f[i][j][k] !== -1) {
            return f[i][j][k];
        }
        let ans = 0;
        for (let d = 0; d < 4; ++d) {
            const [x, y] = [i + dirs[d], j + dirs[d + 1]];
            ans = (ans + dfs(x, y, k - 1)) % mod;
        }
        return (f[i][j][k] = ans);
    };
    return dfs(startRow, startColumn, maxMove);
}

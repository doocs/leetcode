function maxPathScore(grid: number[][], k: number): number {
    const m = grid.length;
    const n = grid[0].length;
    const inf = 1 << 30;

    const f: number[][][] = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => Array(k + 1).fill(-1)),
    );

    const dfs = (i: number, j: number, k: number): number => {
        if (i < 0 || j < 0 || k < 0) {
            return -inf;
        }
        if (i === 0 && j === 0) {
            return 0;
        }
        if (f[i][j][k] !== -1) {
            return f[i][j][k];
        }

        let res = grid[i][j];
        let nk = k;
        if (grid[i][j] !== 0) {
            --nk;
        }

        const a = dfs(i - 1, j, nk);
        const b = dfs(i, j - 1, nk);
        res += Math.max(a, b);

        f[i][j][k] = res;
        return res;
    };

    const ans = dfs(m - 1, n - 1, k);
    return ans < 0 ? -1 : ans;
}

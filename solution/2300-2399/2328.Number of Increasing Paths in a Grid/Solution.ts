function countPaths(grid: number[][]): number {
    const mod = 1e9 + 7;
    const m = grid.length;
    const n = grid[0].length;
    const f = new Array(m).fill(0).map(() => new Array(n).fill(0));
    const dfs = (i: number, j: number): number => {
        if (f[i][j]) {
            return f[i][j];
        }
        let ans = 1;
        const dirs: number[] = [-1, 0, 1, 0, -1];
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[i][j] < grid[x][y]) {
                ans = (ans + dfs(x, y)) % mod;
            }
        }
        return (f[i][j] = ans);
    };
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            ans = (ans + dfs(i, j)) % mod;
        }
    }
    return ans;
}

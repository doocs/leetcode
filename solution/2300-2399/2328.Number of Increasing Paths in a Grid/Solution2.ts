function countPaths(grid: number[][]): number {
    const mod = 1e9 + 7;
    const m = grid.length;
    const n = grid[0].length;
    const f: number[][] = Array.from({ length: m }, () => Array(n).fill(1));
    const cells: number[][] = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            cells.push([grid[i][j], i, j]);
        }
    }
    cells.sort((a, b) => b[0] - a[0]);
    const dirs = [-1, 0, 1, 0, -1];
    for (const [_, i, j] of cells) {
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[i][j] < grid[x][y]) {
                f[i][j] = (f[i][j] + f[x][y]) % mod;
            }
        }
    }
    let ans = 0;
    for (const row of f) {
        for (const v of row) {
            ans = (ans + v) % mod;
        }
    }
    return ans;
}

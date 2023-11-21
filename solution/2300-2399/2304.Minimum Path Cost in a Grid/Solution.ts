function minPathCost(grid: number[][], moveCost: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const f = grid[0];
    for (let i = 1; i < m; ++i) {
        const g: number[] = Array(n).fill(Infinity);
        for (let j = 0; j < n; ++j) {
            for (let k = 0; k < n; ++k) {
                g[j] = Math.min(g[j], f[k] + moveCost[grid[i - 1][k]][j] + grid[i][j]);
            }
        }
        f.splice(0, n, ...g);
    }
    return Math.min(...f);
}

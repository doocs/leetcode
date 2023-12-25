function shiftGrid(grid: number[][], k: number): number[][] {
    const [m, n] = [grid.length, grid[0].length];
    const ans: number[][] = Array.from({ length: m }, () => Array.from({ length: n }, () => 0));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            const idx = (i * n + j + k) % (m * n);
            const [x, y] = [Math.floor(idx / n), idx % n];
            ans[x][y] = grid[i][j];
        }
    }
    return ans;
}

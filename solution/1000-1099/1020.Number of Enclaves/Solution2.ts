function numEnclaves(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const dirs = [-1, 0, 1, 0, -1];
    const q: number[][] = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] === 1 && (i === 0 || i === m - 1 || j === 0 || j === n - 1)) {
                q.push([i, j]);
                grid[i][j] = 0;
            }
        }
    }
    while (q.length) {
        const [i, j] = q.shift()!;
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y <= n && grid[x][y] === 1) {
                q.push([x, y]);
                grid[x][y] = 0;
            }
        }
    }
    let ans = 0;
    for (const row of grid) {
        for (const v of row) {
            ans += v;
        }
    }
    return ans;
}

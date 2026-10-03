function countIslands(grid: number[][], k: number): number {
    const m = grid.length;
    const n = grid[0].length;
    const dirs = [-1, 0, 1, 0, -1];
    const stk: number[][] = [];
    let ans = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === 0) {
                continue;
            }
            let s = grid[i][j];
            grid[i][j] = 0;
            stk.push([i, j]);
            while (stk.length) {
                const [x0, y0] = stk.pop()!;
                for (let d = 0; d < 4; d++) {
                    const x = x0 + dirs[d];
                    const y = y0 + dirs[d + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0) {
                        s += grid[x][y];
                        grid[x][y] = 0;
                        stk.push([x, y]);
                    }
                }
            }
            if (s % k === 0) {
                ans++;
            }
        }
    }
    return ans;
}

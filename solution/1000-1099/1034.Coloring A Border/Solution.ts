function colorBorder(
    grid: number[][],
    row: number,
    col: number,
    color: number,
): number[][] {
    const m = grid.length;
    const n = grid[0].length;
    const vis = new Array(m).fill(0).map(() => new Array(n).fill(false));
    const dirs = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number, c: number) => {
        vis[i][j] = true;
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n) {
                if (!vis[x][y]) {
                    if (grid[x][y] == c) {
                        dfs(x, y, c);
                    } else {
                        grid[i][j] = color;
                    }
                }
            } else {
                grid[i][j] = color;
            }
        }
    };
    dfs(row, col, grid[row][col]);
    return grid;
}

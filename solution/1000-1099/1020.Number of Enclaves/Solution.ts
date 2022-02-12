function numEnclaves(grid: number[][]): number {
    let res = 0;
    const m = grid.length;
    const n = grid[0].length;
    const dfs = (y: number, x: number) => {
        if (x < 0 || x >= n || y < 0 || y >= m || grid[y][x] === 0) {
            return;
        }
        grid[y][x] = 0;
        dfs(y + 1, x);
        dfs(y, x + 1);
        dfs(y - 1, x);
        dfs(y, x - 1);
    };
    for (let i = 0; i < n; i++) {
        dfs(0, i);
        dfs(m - 1, i);
    }
    for (let i = 0; i < m; i++) {
        dfs(i, 0);
        dfs(i, n - 1);
    }
    for (let i = 1; i < m - 1; i++) {
        for (let j = 1; j < n - 1; j++) {
            if (grid[i][j] === 1) {
                res++;
            }
        }
    }
    return res;
}

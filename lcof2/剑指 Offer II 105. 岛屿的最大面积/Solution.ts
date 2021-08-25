function maxAreaOfIsland(grid: number[][]): number {
    let m = grid.length, n = grid[0].length;
    let res = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == 1) {
                res = Math.max(dfs(grid, i, j), res);
            }
        }
    }
    return res;
};

function dfs(grid: number[][], i: number, j: number): number {
    let m = grid.length, n = grid[0].length;
    if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || grid[i][j] == 0) {
        return 0;
    }
    grid[i][j] = 0;
    let res = 1;
    for (let [dx, dy] of [[0, 1], [0, -1], [1, 0], [-1, 0]]) {
        res += dfs(grid, i + dx, j + dy);
    }
    return res;
}
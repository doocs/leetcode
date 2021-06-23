function numIslands(grid: string[][]): number {
    let m = grid.length, n = grid[0].length;
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == '1') {
                dfs(grid, i, j);
                ++ans;
            }
        }
    }
    return ans;
};

function dfs(grid: string[][], i: number, j: number) {
    let m = grid.length, n = grid[0].length;
    if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || grid[i][j] == '0') {
        return;
    }
    grid[i][j] = '0';
    for (let [dx, dy] of [[0, 1], [0, -1], [1, 0], [-1, 0]]) {
        let x = i + dx, y = j + dy;
        dfs(grid, x, y);
    }
}
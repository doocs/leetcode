function islandPerimeter(grid: number[][]): number {
    let m = grid.length,
        n = grid[0].length;
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            let top = 0,
                left = 0;
            if (i > 0) {
                top = grid[i - 1][j];
            }
            if (j > 0) {
                left = grid[i][j - 1];
            }
            let cur = grid[i][j];
            if (cur != top) ++ans;
            if (cur != left) ++ans;
        }
    }
    // 最后一行， 最后一列
    for (let i = 0; i < m; ++i) {
        if (grid[i][n - 1] == 1) ++ans;
    }
    for (let j = 0; j < n; ++j) {
        if (grid[m - 1][j] == 1) ++ans;
    }
    return ans;
}

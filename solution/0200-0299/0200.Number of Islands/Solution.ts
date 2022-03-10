function numIslands(grid: string[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let ans = 0;
    function dfs(i, j) {
        grid[i][j] = '0';
        const dirs = [-1, 0, 1, 0, -1];
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                dfs(x, y);
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == '1') {
                dfs(i, j);
                ++ans;
            }
        }
    }
    return ans;
}

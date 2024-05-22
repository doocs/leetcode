function numIslands(grid: string[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let ans = 0;

    const dfs = (i: number, j: number) => {
        if (grid[i]?.[j] !== '1') return;

        grid[i][j] = '0';
        dfs(i + 1, j);
        dfs(i - 1, j);
        dfs(i, j + 1);
        dfs(i, j - 1);
    };

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

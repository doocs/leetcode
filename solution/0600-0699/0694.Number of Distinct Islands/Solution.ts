function numDistinctIslands(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const paths: Set<string> = new Set();
    const path: number[] = [];
    const dirs: number[] = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number, k: number) => {
        grid[i][j] = 0;
        path.push(k);
        for (let h = 1; h < 5; ++h) {
            const [x, y] = [i + dirs[h - 1], j + dirs[h]];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y]) {
                dfs(x, y, h);
            }
        }
        path.push(k);
    };
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j]) {
                dfs(i, j, 0);
                paths.add(path.join(','));
                path.length = 0;
            }
        }
    }
    return paths.size;
}

function numIslands(grid: string[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let ans = 0;
    function bfs(i, j) {
        grid[i][j] = '0';
        let q = [[i, j]];
        const dirs = [-1, 0, 1, 0, -1];
        while (q.length) {
            [i, j] = q.shift();
            for (let k = 0; k < 4; ++k) {
                const x = i + dirs[k];
                const y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                    q.push([x, y]);
                    grid[x][y] = '0';
                }
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == '1') {
                bfs(i, j);
                ++ans;
            }
        }
    }
    return ans;
}

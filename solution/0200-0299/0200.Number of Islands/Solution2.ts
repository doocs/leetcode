function numIslands(grid: string[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let ans = 0;
    const bfs = (i: number, j: number) => {
        grid[i][j] = '0';
        const q = [[i, j]];
        const dirs = [-1, 0, 1, 0, -1];
        for (const [i, j] of q) {
            for (let k = 0; k < 4; ++k) {
                const x = i + dirs[k];
                const y = j + dirs[k + 1];
                if (grid[x]?.[y] == '1') {
                    q.push([x, y]);
                    grid[x][y] = '0';
                }
            }
        }
    };
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

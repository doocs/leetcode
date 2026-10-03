function numIslands(grid: string[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let ans = 0;
    const dirs = [-1, 0, 1, 0, -1];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] !== '1') {
                continue;
            }
            grid[i][j] = '0';
            const stk: number[][] = [[i, j]];
            while (stk.length) {
                const [a, b] = stk.pop()!;
                for (let k = 0; k < 4; ++k) {
                    const x = a + dirs[k];
                    const y = b + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] === '1') {
                        grid[x][y] = '0';
                        stk.push([x, y]);
                    }
                }
            }
            ans++;
        }
    }
    return ans;
}

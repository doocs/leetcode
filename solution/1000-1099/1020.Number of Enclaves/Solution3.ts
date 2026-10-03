function numEnclaves(grid: number[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    const dirs = [-1, 0, 1, 0, -1];
    const flood = (i: number, j: number) => {
        grid[i][j] = 0;
        const stk: number[][] = [[i, j]];
        while (stk.length) {
            const [a, b] = stk.pop()!;
            for (let k = 0; k < 4; ++k) {
                const x = a + dirs[k];
                const y = b + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] === 1) {
                    grid[x][y] = 0;
                    stk.push([x, y]);
                }
            }
        }
    };
    for (let j = 0; j < n; ++j) {
        for (const i of [0, m - 1]) {
            if (grid[i][j] === 1) {
                flood(i, j);
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (const j of [0, n - 1]) {
            if (grid[i][j] === 1) {
                flood(i, j);
            }
        }
    }
    return grid.flat().reduce((acc, cur) => acc + cur, 0);
}

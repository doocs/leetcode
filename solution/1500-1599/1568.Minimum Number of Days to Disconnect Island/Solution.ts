function minDays(grid: number[][]): number {
    const [m, n] = [grid.length, grid[0].length];

    const dfs = (i: number, j: number) => {
        if (i < 0 || m <= i || j < 0 || n <= j || [0, 2].includes(grid[i][j])) return;

        grid[i][j] = 2;
        const dir = [-1, 0, 1, 0, -1];
        for (let k = 0; k < 4; k++) {
            const [y, x] = [i + dir[k], j + dir[k + 1]];
            dfs(y, x);
        }
    };

    const count = () => {
        let c = 0;

        for (let i = 0; i < m; i++) {
            for (let j = 0; j < n; j++) {
                if (grid[i][j] === 1) {
                    dfs(i, j);
                    c++;
                }
            }
        }

        for (let i = 0; i < m; i++) {
            for (let j = 0; j < n; j++) {
                if (grid[i][j] === 2) {
                    grid[i][j] = 1;
                }
            }
        }

        return c;
    };

    if (count() !== 1) return 0;

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === 1) {
                grid[i][j] = 0;

                if (count() !== 1) return 1;

                grid[i][j] = 1;
            }
        }
    }

    return 2;
}

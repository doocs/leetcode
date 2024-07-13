function minimumArea(grid: number[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    let [x1, y1] = [m, n];
    let [x2, y2] = [0, 0];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] === 1) {
                x1 = Math.min(x1, i);
                y1 = Math.min(y1, j);
                x2 = Math.max(x2, i);
                y2 = Math.max(y2, j);
            }
        }
    }
    return (x2 - x1 + 1) * (y2 - y1 + 1);
}

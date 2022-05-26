function projectionArea(grid: number[][]): number {
    const n = grid.length;
    let res = grid.reduce(
        (r, v) => r + v.reduce((r, v) => r + (v === 0 ? 0 : 1), 0),
        0,
    );
    for (let i = 0; i < n; i++) {
        let xMax = 0;
        let yMax = 0;
        for (let j = 0; j < n; j++) {
            xMax = Math.max(xMax, grid[i][j]);
            yMax = Math.max(yMax, grid[j][i]);
        }
        res += xMax + yMax;
    }
    return res;
}

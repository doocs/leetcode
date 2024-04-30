function projectionArea(grid: number[][]): number {
    const xy: number = grid.flat().filter(v => v > 0).length;
    const yz: number = grid.reduce((acc, row) => acc + Math.max(...row), 0);
    const zx: number = grid[0]
        .map((_, i) => Math.max(...grid.map(row => row[i])))
        .reduce((acc, val) => acc + val, 0);
    return xy + yz + zx;
}

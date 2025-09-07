function reverseSubmatrix(grid: number[][], x: number, y: number, k: number): number[][] {
    for (let i = x; i < x + Math.floor(k / 2); i++) {
        const i2 = x + k - 1 - (i - x);
        for (let j = y; j < y + k; j++) {
            [grid[i][j], grid[i2][j]] = [grid[i2][j], grid[i][j]];
        }
    }
    return grid;
}

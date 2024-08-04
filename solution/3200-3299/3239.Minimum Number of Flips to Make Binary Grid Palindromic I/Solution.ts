function minFlips(grid: number[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    let [cnt1, cnt2] = [0, 0];
    for (const row of grid) {
        for (let j = 0; j < n / 2; ++j) {
            if (row[j] !== row[n - 1 - j]) {
                ++cnt1;
            }
        }
    }
    for (let j = 0; j < n; ++j) {
        for (let i = 0; i < m / 2; ++i) {
            if (grid[i][j] !== grid[m - 1 - i][j]) {
                ++cnt2;
            }
        }
    }
    return Math.min(cnt1, cnt2);
}

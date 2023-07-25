function deleteGreatestValue(grid: number[][]): number {
    for (const row of grid) {
        row.sort((a, b) => a - b);
    }

    let ans = 0;
    for (let j = 0; j < grid[0].length; ++j) {
        let t = 0;
        for (let i = 0; i < grid.length; ++i) {
            t = Math.max(t, grid[i][j]);
        }
        ans += t;
    }

    return ans;
}

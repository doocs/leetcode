function maxSum(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let ans = 0;
    for (let i = 1; i < m - 1; ++i) {
        for (let j = 1; j < n - 1; ++j) {
            let s = -grid[i][j - 1] - grid[i][j + 1];
            for (let x = i - 1; x <= i + 1; ++x) {
                for (let y = j - 1; y <= j + 1; ++y) {
                    s += grid[x][y];
                }
            }
            ans = Math.max(ans, s);
        }
    }
    return ans;
}

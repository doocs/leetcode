function minPathSum(grid: number[][]): number {
    let m = grid.length,
        n = grid[0].length;
    let dp = Array.from({ length: m }, v => new Array(n).fill(0));
    dp[0][0] = grid[0][0];
    for (let i = 1; i < m; ++i) {
        dp[i][0] = dp[i - 1][0] + grid[i][0];
    }
    for (let j = 1; j < n; ++j) {
        dp[0][j] = dp[0][j - 1] + grid[0][j];
    }
    // dp
    for (let i = 1; i < m; ++i) {
        for (let j = 1; j < n; ++j) {
            let cur = grid[i][j];
            dp[i][j] = cur + Math.min(dp[i - 1][j], dp[i][j - 1]);
        }
    }
    return dp[m - 1][n - 1];
}

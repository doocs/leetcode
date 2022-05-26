/**
 * @param {number[][]} grid
 * @return {number}
 */
var maxValue = function (grid) {
    const m = grid.length;
    const n = grid[0].length;
    let dp = new Array(m + 1);
    for (let i = 0; i < m + 1; ++i) {
        dp[i] = new Array(n + 1).fill(0);
    }
    for (let i = 1; i < m + 1; ++i) {
        for (let j = 1; j < n + 1; ++j) {
            dp[i][j] =
                Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
        }
    }
    return dp[m][n];
};

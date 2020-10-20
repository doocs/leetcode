/**
 * @param {number[][]} grid
 * @return {number}
 */
var maxValue = function (grid) {
  let row = grid.length;
  let col = grid[0].length;
  let dp = [...new Array(row + 1)].map(() => Array(col + 1).fill(0));
  for (let i = 1; i <= row; i++) {
    for (let j = 1; j <= col; j++) {
      dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
    }
  }
  return dp[row][col];
};

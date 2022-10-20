function maxSum(grid: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    let threeSum = Array.from({ length: m }, () => new Array(n - 2).fill(0));
    for (let i = 0; i < m; i++) {
        for (let j = 1; j < n - 1; j++) {
            threeSum[i][j - 1] = grid[i][j - 1] + grid[i][j] + grid[i][j + 1];
        }
    }
    let ans = 0;
    for (let i = 1; i < m - 1; i++) {
        for (let j = 1; j < n - 1; j++) {
            ans = Math.max(
                ans,
                threeSum[i - 1][j - 1] + grid[i][j] + threeSum[i + 1][j - 1],
            );
        }
    }
    return ans;
}

func maxValue(grid [][]int) int {
    m, n := len(grid), len(grid[0])
    dp := make([][]int, m + 1)
    for i := 0; i < m + 1; i++ {
        dp[i] = make([]int, n + 1)
    }
    for i := 1; i < m + 1; i++ {
        for j := 1; j < n + 1; j++ {
            dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1]
        }
    }
    return dp[m][n]
}

func max(a, b int) int {
    if (a > b) {
        return a
    }
    return b
}
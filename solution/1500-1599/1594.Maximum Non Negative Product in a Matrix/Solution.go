func maxProductPath(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dp := make([][][]int, m)
	for i := range dp {
		dp[i] = make([][]int, n)
		for j := range dp[i] {
			dp[i][j] = make([]int, 2)
		}
	}
	dp[0][0] = []int{grid[0][0], grid[0][0]}
	for i := 1; i < m; i++ {
		dp[i][0][0] = dp[i-1][0][0] * grid[i][0]
		dp[i][0][1] = dp[i-1][0][1] * grid[i][0]
	}
	for j := 1; j < n; j++ {
		dp[0][j][0] = dp[0][j-1][0] * grid[0][j]
		dp[0][j][1] = dp[0][j-1][1] * grid[0][j]
	}
	for i := 1; i < m; i++ {
		for j := 1; j < n; j++ {
			v := grid[i][j]
			if v >= 0 {
				dp[i][j][0] = min(dp[i-1][j][0], dp[i][j-1][0]) * v
				dp[i][j][1] = max(dp[i-1][j][1], dp[i][j-1][1]) * v
			} else {
				dp[i][j][0] = max(dp[i-1][j][1], dp[i][j-1][1]) * v
				dp[i][j][1] = min(dp[i-1][j][0], dp[i][j-1][0]) * v
			}
		}
	}
	ans := dp[m-1][n-1][1]
	if ans < 0 {
		return -1
	}
	var mod int = 1e9 + 7
	return ans % mod
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
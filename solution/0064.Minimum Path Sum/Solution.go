
func minPathSum(grid [][]int) int {
	yl := len(grid)
	if yl == 0 {
		return 0
	}
	xl := len(grid[0])
	if xl == 0 {
		return 0
	}
	// malloc
	dp := make([][]int, yl)
	for i, _ := range dp {
		dp[i] = make([]int, xl)
	}
	dp[0][0] = grid[0][0]
	for i := 1; i < yl; i++ {
		dp[i][0] = grid[i][0] + dp[i-1][0]
	}
	for j := 1; j < xl; j++ {
		dp[0][j] = grid[0][j] + dp[0][j-1]
	}
	for i := 1; i < yl; i++ {
		for j := 1; j < xl; j++ {
			dp[i][j] = grid[i][j] + minInt(dp[i-1][j], dp[i][j-1])
		}
	}
	return dp[yl-1][xl-1]
}

func minInt(x, y int) int {
	if x < y {
		return x
	}
	return y
} 

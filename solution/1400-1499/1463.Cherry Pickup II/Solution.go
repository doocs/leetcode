func cherryPickup(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dp := make([][][]int, m)
	valid := make([][][]bool, m)
	for i := range dp {
		dp[i] = make([][]int, n)
		valid[i] = make([][]bool, n)
		for j1 := range dp[i] {
			dp[i][j1] = make([]int, n)
			valid[i][j1] = make([]bool, n)
		}
	}
	dp[0][0][n-1] = grid[0][0] + grid[0][n-1]
	valid[0][0][n-1] = true
	for i := 1; i < m; i++ {
		for j1 := 0; j1 < n; j1++ {
			for j2 := 0; j2 < n; j2++ {
				t := grid[i][j1]
				if j1 != j2 {
					t += grid[i][j2]
				}
				ok := false
				for y1 := j1 - 1; y1 <= j1+1; y1++ {
					for y2 := j2 - 1; y2 <= j2+1; y2++ {
						if y1 >= 0 && y1 < n && y2 >= 0 && y2 < n && valid[i-1][y1][y2] {
							dp[i][j1][j2] = max(dp[i][j1][j2], dp[i-1][y1][y2]+t)
							ok = true
						}
					}
				}
				valid[i][j1][j2] = ok
			}
		}
	}
	ans := 0
	for j1 := 0; j1 < n; j1++ {
		for j2 := 0; j2 < n; j2++ {
			ans = max(ans, dp[m-1][j1][j2])
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
func cherryPickup(grid [][]int) int {
	n := len(grid)
	dp := make([][][]int, (n<<1)-1)
	for i := range dp {
		dp[i] = make([][]int, n)
		for j := range dp[i] {
			dp[i][j] = make([]int, n)
		}
	}
	dp[0][0][0] = grid[0][0]
	for k := 1; k < (n<<1)-1; k++ {
		for i1 := 0; i1 < n; i1++ {
			for i2 := 0; i2 < n; i2++ {
				dp[k][i1][i2] = int(-1e9)
				j1, j2 := k-i1, k-i2
				if j1 < 0 || j1 >= n || j2 < 0 || j2 >= n || grid[i1][j1] == -1 || grid[i2][j2] == -1 {
					continue
				}
				t := grid[i1][j1]
				if i1 != i2 {
					t += grid[i2][j2]
				}
				for x1 := i1 - 1; x1 <= i1; x1++ {
					for x2 := i2 - 1; x2 <= i2; x2++ {
						if x1 >= 0 && x2 >= 0 {
							dp[k][i1][i2] = max(dp[k][i1][i2], dp[k-1][x1][x2]+t)
						}
					}
				}
			}
		}
	}
	return max(0, dp[n*2-2][n-1][n-1])
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
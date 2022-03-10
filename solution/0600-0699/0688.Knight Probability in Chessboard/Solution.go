func knightProbability(n int, k int, row int, column int) float64 {
	dp := make([][][]float64, k+1)
	dirs := []int{-2, -1, 2, 1, -2, 1, 2, -1, -2}
	for l := range dp {
		dp[l] = make([][]float64, n)
		for i := 0; i < n; i++ {
			dp[l][i] = make([]float64, n)
			for j := 0; j < n; j++ {
				if l == 0 {
					dp[l][i][j] = 1
				} else {
					for d := 0; d < 8; d++ {
						x, y := i+dirs[d], j+dirs[d+1]
						if 0 <= x && x < n && 0 <= y && y < n {
							dp[l][i][j] += dp[l-1][x][y] / 8
						}
					}
				}
			}
		}
	}
	return dp[k][row][column]
}
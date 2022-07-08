func stoneGame(piles []int) bool {
	n := len(piles)
	dp := make([][]int, n)
	for i, v := range piles {
		dp[i] = make([]int, n)
		dp[i][i] = v
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			dp[i][j] = max(piles[i]-dp[i+1][j], piles[j]-dp[i][j-1])
		}
	}
	return dp[0][n-1] > 0
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
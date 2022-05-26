func getMoneyAmount(n int) int {
	dp := make([][]int, n+10)
	for i := 0; i < len(dp); i++ {
		dp[i] = make([]int, n+10)
	}
	for l := 2; l <= n; l++ {
		for i := 1; i+l-1 <= n; i++ {
			j := i + l - 1
			dp[i][j] = math.MaxInt32
			for k := i; k <= j; k++ {
				t := max(dp[i][k-1], dp[k+1][j]) + k
				dp[i][j] = min(dp[i][j], t)
			}
		}
	}
	return dp[1][n]
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
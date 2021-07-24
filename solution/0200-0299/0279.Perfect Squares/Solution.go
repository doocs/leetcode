func numSquares(n int) int {
	dp := make([]int, n+1)
	for i := 1; i <= n; i++ {
		mi := 100000
		for j := 1; j*j <= i; j++ {
			mi = min(mi, dp[i-j*j])
		}
		dp[i] = mi + 1
	}
	return dp[n]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
func maxA(n int) int {
	dp := make([]int, n+1)
	for i := range dp {
		dp[i] = i
	}
	for i := 3; i < n+1; i++ {
		for j := 2; j < i-1; j++ {
			dp[i] = max(dp[i], dp[j-1]*(i-j))
		}
	}
	return dp[n]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
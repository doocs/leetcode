const mod int = 1e9 + 7

func kInversePairs(n int, k int) int {
	dp := make([]int, k+1)
	pre := make([]int, k+2)
	for i := 1; i <= n; i++ {
		dp[0] = 1

		// dp[i][j] = dp[i - 1][j - (i - 1)] + ... + dp[i - 1][j]
		for j := 1; j <= k; j++ {
			dp[j] = (pre[j+1] - pre[max(0, j-i+1)] + mod) % mod
		}

		for j := 1; j <= k+1; j++ {
			pre[j] = (pre[j-1] + dp[j-1]) % mod
		}
	}
	return dp[k]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

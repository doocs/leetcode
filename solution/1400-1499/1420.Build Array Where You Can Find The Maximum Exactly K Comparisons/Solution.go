func numOfArrays(n int, m int, k int) int {
	if k == 0 {
		return 0
	}
	mod := int(1e9) + 7
	dp := make([][][]int, n+1)
	for i := range dp {
		dp[i] = make([][]int, k+1)
		for j := range dp[i] {
			dp[i][j] = make([]int, m+1)
		}
	}
	for i := 1; i <= m; i++ {
		dp[1][1][i] = 1
	}
	for i := 2; i <= n; i++ {
		for c := 1; c <= k && c <= i; c++ {
			for j := 1; j <= m; j++ {
				dp[i][c][j] = (dp[i-1][c][j] * j) % mod
				for j0 := 1; j0 < j; j0++ {
					dp[i][c][j] = (dp[i][c][j] + dp[i-1][c-1][j0]) % mod
				}
			}
		}
	}
	ans := 0
	for i := 1; i <= m; i++ {
		ans = (ans + dp[n][k][i]) % mod
	}
	return ans
}
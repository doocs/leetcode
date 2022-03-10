const _mod int = 1e9 + 7

func checkRecord(n int) int {
	dp := make([][][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([][]int, 2)
		for j := 0; j < 2; j++ {
			dp[i][j] = make([]int, 3)
		}
	}

	// base case
	dp[0][0][0] = 1
	dp[0][0][1] = 1
	dp[0][1][0] = 1

	for i := 1; i < n; i++ {
		// A
		dp[i][1][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2]) % _mod
		// L
		dp[i][0][1] = dp[i-1][0][0]
		dp[i][0][2] = dp[i-1][0][1]
		dp[i][1][1] = dp[i-1][1][0]
		dp[i][1][2] = dp[i-1][1][1]
		// P
		dp[i][0][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2]) % _mod
		dp[i][1][0] = (dp[i][1][0] + dp[i-1][1][0] + dp[i-1][1][1] + dp[i-1][1][2]) % _mod
	}

	var ans int
	for j := 0; j < 2; j++ {
		for k := 0; k < 3; k++ {
			ans = (ans + dp[n-1][j][k]) % _mod
		}
	}
	return ans
}

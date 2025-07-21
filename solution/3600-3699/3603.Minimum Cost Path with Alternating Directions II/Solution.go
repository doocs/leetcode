func minCost(m int, n int, cost [][]int) int64 {
	dp := make([]int64, n)
	for i := 0; i < n; i++ {
		dp[i] = int64(i + 1)
	}
	for i := 1; i < n; i++ {
		dp[i] += dp[i-1] + int64(cost[0][i])
	}

	for y := 1; y < m; y++ {
		dp[0] += int64(cost[y][0]) + int64(y+1)
		for x := 1; x < n; x++ {
			enter := int64(y+1) * int64(x+1)
			dp[x] = min64(dp[x], dp[x-1]) + int64(cost[y][x]) + enter
		}
	}

	return dp[n-1] - int64(cost[m-1][n-1])
}

func min64(a, b int64) int64 {
	if a < b {
		return a
	}
	return b
}

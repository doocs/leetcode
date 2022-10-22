func maxValue(events [][]int, k int) int {
	sort.Slice(events, func(i, j int) bool { return events[i][1] < events[j][1] })
	n := len(events)
	dp := make([][]int, n+1)
	for i := range dp {
		dp[i] = make([]int, k+1)
	}
	for i, event := range events {
		h := sort.Search(i, func(k int) bool { return events[k][1] >= event[0] })
		for j := 1; j <= k; j++ {
			dp[i+1][j] = max(dp[i][j], dp[h][j-1]+event[2])
		}
	}
	return dp[n][k]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
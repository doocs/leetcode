func minDeletionSize(strs []string) int {
	n := len(strs[0])
	dp := make([]int, n)
	mx := 1
	dp[0] = 1
	check := func(i, j int) bool {
		for _, s := range strs {
			if s[i] < s[j] {
				return false
			}
		}
		return true
	}
	for i := 1; i < n; i++ {
		dp[i] = 1
		for j := 0; j < i; j++ {
			if check(i, j) {
				dp[i] = max(dp[i], dp[j]+1)
			}
		}
		mx = max(mx, dp[i])
	}
	return n - mx
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
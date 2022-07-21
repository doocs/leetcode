func respace(dictionary []string, sentence string) int {
	s := map[string]bool{}
	for _, v := range dictionary {
		s[v] = true
	}
	n := len(sentence)
	dp := make([]int, n+1)
	for i := 1; i <= n; i++ {
		dp[i] = dp[i-1] + 1
		for j := 0; j < i; j++ {
			if s[sentence[j:i]] {
				dp[i] = min(dp[i], dp[j])
			}
		}
	}
	return dp[n]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
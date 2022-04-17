func lastStoneWeightII(stones []int) int {
	s := 0
	for _, v := range stones {
		s += v
	}
	n := s >> 1
	dp := make([]int, n+1)
	for _, v := range stones {
		for j := n; j >= v; j-- {
			dp[j] = max(dp[j], dp[j-v]+v)
		}
	}
	return s - dp[n]*2
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
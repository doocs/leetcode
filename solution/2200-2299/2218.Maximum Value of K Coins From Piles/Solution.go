func maxValueOfCoins(piles [][]int, k int) int {
	var presum [][]int
	for _, p := range piles {
		m := len(p)
		s := make([]int, m+1)
		for i, v := range p {
			s[i+1] = s[i] + v
		}
		presum = append(presum, s)
	}
	dp := make([]int, k+1)
	for _, s := range presum {
		for j := k; j >= 0; j-- {
			for idx, v := range s {
				if j >= idx {
					dp[j] = max(dp[j], dp[j-idx]+v)
				}
			}
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
func findLongestChain(pairs [][]int) int {
	sort.Slice(pairs, func(i, j int) bool {
		return pairs[i][0] < pairs[j][0]
	})
	n := len(pairs)
	dp := make([]int, n)
	ans := 0
	for i := range pairs {
		dp[i] = 1
		c := pairs[i][0]
		for j := range pairs[:i] {
			b := pairs[j][1]
			if b < c {
				dp[i] = max(dp[i], dp[j]+1)
			}
		}
		ans = max(ans, dp[i])
	}
	return ans
}
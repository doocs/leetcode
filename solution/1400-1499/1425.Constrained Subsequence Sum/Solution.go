func constrainedSubsetSum(nums []int, k int) int {
	n := len(nums)
	dp := make([]int, n)
	ans := math.MinInt32
	q := []int{}
	for i, v := range nums {
		if len(q) > 0 && i-q[0] > k {
			q = q[1:]
		}
		dp[i] = v
		if len(q) > 0 && dp[q[0]] > 0 {
			dp[i] += dp[q[0]]
		}
		for len(q) > 0 && dp[q[len(q)-1]] < dp[i] {
			q = q[:len(q)-1]
		}
		q = append(q, i)
		ans = max(ans, dp[i])
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
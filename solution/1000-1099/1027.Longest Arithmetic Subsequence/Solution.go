func longestArithSeqLength(nums []int) int {
	n := len(nums)
	dp := make([][]int, n)
	for i := range dp {
		dp[i] = make([]int, 1001)
	}
	ans := 0
	for i := 1; i < n; i++ {
		for j := 0; j < i; j++ {
			d := nums[i] - nums[j] + 500
			dp[i][d] = max(dp[i][d], dp[j][d]+1)
			ans = max(ans, dp[i][d])
		}
	}
	return ans + 1
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
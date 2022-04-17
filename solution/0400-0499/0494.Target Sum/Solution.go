func findTargetSumWays(nums []int, target int) int {
	s := 0
	for _, v := range nums {
		s += v
	}
	if s < target || (s-target)%2 != 0 {
		return 0
	}
	m, n := len(nums), (s-target)/2
	dp := make([]int, n+1)
	dp[0] = 1
	for i := 1; i <= m; i++ {
		for j := n; j >= nums[i-1]; j-- {
			dp[j] += dp[j-nums[i-1]]
		}
	}
	return dp[n]
}
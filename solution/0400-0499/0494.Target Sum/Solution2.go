func findTargetSumWays(nums []int, target int) int {
	s := 0
	for _, v := range nums {
		s += v
	}
	if s < target || (s-target)%2 != 0 {
		return 0
	}
	n := (s - target) / 2
	dp := make([]int, n+1)
	dp[0] = 1
	for _, v := range nums {
		for j := n; j >= v; j-- {
			dp[j] += dp[j-v]
		}
	}
	return dp[n]
}
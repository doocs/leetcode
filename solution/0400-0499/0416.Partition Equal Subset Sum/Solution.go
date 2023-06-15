func canPartition(nums []int) bool {
	s := 0
	for _, v := range nums {
		s += v
	}
	if s%2 != 0 {
		return false
	}
	n := s >> 1
	dp := make([]bool, n+1)
	dp[0] = true
	for _, v := range nums {
		for j := n; j >= v; j-- {
			dp[j] = dp[j] || dp[j-v]
		}
	}
	return dp[n]
}
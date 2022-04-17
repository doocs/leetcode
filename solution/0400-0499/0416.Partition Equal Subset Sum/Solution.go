func canPartition(nums []int) bool {
	s := 0
	for _, v := range nums {
		s += v
	}
	if s%2 != 0 {
		return false
	}
	m, n := len(nums), s>>1
	dp := make([]bool, n+1)
	dp[0] = true
	for i := 1; i <= m; i++ {
		for j := n; j >= nums[i-1]; j-- {
			dp[j] = dp[j] || dp[j-nums[i-1]]
		}
	}
	return dp[n]
}
func validPartition(nums []int) bool {
	n := len(nums)
	dp := make([]bool, n+1)
	dp[0] = true
	for i := 2; i <= n; i++ {
		if nums[i-1] == nums[i-2] {
			dp[i] = dp[i] || dp[i-2]
		}
		if i > 2 && nums[i-1] == nums[i-2] && nums[i-2] == nums[i-3] {
			dp[i] = dp[i] || dp[i-3]
		}
		if i > 2 && nums[i-1]-nums[i-2] == 1 && nums[i-2]-nums[i-3] == 1 {
			dp[i] = dp[i] || dp[i-3]
		}
	}
	return dp[n]
}
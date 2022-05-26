func canPartition(nums []int) bool {
	s := 0
	for _, x := range nums {
		s += x
	}
	if s%2 != 0 {
		return false
	}
	m, n := len(nums), (s>>1)+1
	dp := make([]bool, n)
	dp[0] = true
	if nums[0] < n {
		dp[nums[0]] = true
	}
	for i := 1; i < m; i++ {
		for j := n - 1; j >= nums[i]; j-- {
			dp[j] = dp[j] || dp[j-nums[i]]
		}
	}
	return dp[n-1]
}
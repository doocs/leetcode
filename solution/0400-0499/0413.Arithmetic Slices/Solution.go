func numberOfArithmeticSlices(nums []int) int {
	n := len(nums)
	dp := make([]int, n)
	for i := 2; i < n; i++ {
		if nums[i]-nums[i-1] == nums[i-1]-nums[i-2] {
			dp[i] = 1 + dp[i-1]
		}
	}
	res := 0
	for _, e := range dp {
		res += e
	}
	return res
}
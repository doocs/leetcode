func findTargetSumWays(nums []int, target int) int {
	s := 0
	for _, x := range nums {
		s += x
	}
	if s-target < 0 || (s-target)%2 != 0 {
		return 0
	}
	target = (s-target)/2 + 1
	dp := make([]int, target)
	dp[0] = 1
	for i := 1; i < len(nums)+1; i++ {
		for j := target - 1; j >= nums[i-1]; j-- {
			dp[j] += dp[j-nums[i-1]]
		}
	}
	return dp[target-1]
}
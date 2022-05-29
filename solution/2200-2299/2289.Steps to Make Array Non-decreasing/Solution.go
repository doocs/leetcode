func totalSteps(nums []int) int {
	stk := []int{}
	ans, n := 0, len(nums)
	dp := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && nums[i] > nums[stk[len(stk)-1]] {
			dp[i] = max(dp[i]+1, dp[stk[len(stk)-1]])
			stk = stk[:len(stk)-1]
			ans = max(ans, dp[i])
		}
		stk = append(stk, i)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
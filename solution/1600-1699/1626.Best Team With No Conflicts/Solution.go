func bestTeamScore(scores []int, ages []int) int {
	n := len(ages)
	nums := make([][]int, n)
	for i, age := range ages {
		nums[i] = []int{age, scores[i]}
	}
	sort.Slice(nums, func(i, j int) bool {
		if nums[i][0] != nums[j][0] {
			return nums[i][0] < nums[j][0]
		}
		return nums[i][1] < nums[j][1]
	})
	dp := make([]int, n)
	ans := 0
	for i, num := range nums {
		dp[i] = num[1]
		for j := 0; j < i; j++ {
			if num[1] >= nums[j][1] {
				dp[i] = max(dp[i], dp[j]+num[1])
			}
		}
		ans = max(ans, dp[i])
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
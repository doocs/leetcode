func bestTeamScore(scores []int, ages []int) int {
	n := len(ages)
	var nums [][]int
	for i := 0; i < n; i++ {
		nums = append(nums, []int{scores[i], ages[i]})
	}
	sort.Slice(nums, func(i, j int) bool {
		if nums[i][1] == nums[j][1] {
			return nums[i][0] < nums[j][0]
		}
		return nums[i][1] < nums[j][1]
	})
	dp := make([]int, n)
	res := 0
	for i := 0; i < n; i++ {
		dp[i] = nums[i][0]
		for j := 0; j < i; j++ {
			if nums[j][0] <= nums[i][0] {
				dp[i] = max(dp[i], dp[j]+nums[i][0])
			}
		}
		res = max(res, dp[i])
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
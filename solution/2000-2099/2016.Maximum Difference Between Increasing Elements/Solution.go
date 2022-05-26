func maximumDifference(nums []int) int {
	mi, ans := nums[0], -1
	for i, n := 1, len(nums); i < n; i++ {
		if nums[i] > mi {
			ans = max(ans, nums[i]-mi)
		} else {
			mi = nums[i]
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
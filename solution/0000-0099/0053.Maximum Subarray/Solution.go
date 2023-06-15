func maxSubArray(nums []int) int {
	ans, f := nums[0], nums[0]
	for _, x := range nums[1:] {
		f = max(f, 0) + x
		ans = max(ans, f)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
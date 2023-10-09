func maxSubArray(nums []int) int {
	ans, f := math.MinInt32, math.MinInt32
	for _, x := range nums {
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
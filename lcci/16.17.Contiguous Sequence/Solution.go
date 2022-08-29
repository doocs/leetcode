func maxSubArray(nums []int) int {
	inf := math.MinInt32
	ans, s := inf, inf
	for _, v := range nums {
		s = max(s, 0) + v
		ans = max(ans, s)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
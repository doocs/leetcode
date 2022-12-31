func maximumAlternatingSubarraySum(nums []int) int64 {
	ans := nums[0]
	a, b := nums[0], -(1 << 30)
	for _, v := range nums[1:] {
		c, d := a, b
		a = max(v, d+v)
		b = c - v
		ans = max(ans, max(a, b))
	}
	return int64(ans)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
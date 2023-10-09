func maximumAlternatingSubarraySum(nums []int) int64 {
	const inf = 1 << 60
	ans, f, g := -inf, -inf, -inf
	for _, x := range nums {
		f, g = max(g, 0)+x, f-x
		ans = max(ans, max(f, g))
	}
	return int64(ans)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
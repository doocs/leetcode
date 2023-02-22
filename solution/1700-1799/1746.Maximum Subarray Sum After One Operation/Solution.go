func maxSumAfterOperation(nums []int) int {
	var f, g int
	ans := -(1 << 30)
	for _, x := range nums {
		ff := max(f, 0) + x
		gg := max(max(f, 0)+x*x, g+x)
		f, g = ff, gg
		ans = max(ans, max(f, g))
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
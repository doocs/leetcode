func maxAbsoluteSum(nums []int) (ans int) {
	var f, g int
	for _, x := range nums {
		f = max(f, 0) + x
		g = min(g, 0) + x
		ans = max(ans, max(f, abs(g)))
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
func maxAlternatingSum(nums []int) int64 {
	var f, g int
	for _, x := range nums {
		f, g = max(g-x, f), max(f+x, g)
	}
	return int64(max(f, g))
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
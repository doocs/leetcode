func massage(nums []int) int {
	f, g := 0, 0
	for _, x := range nums {
		f, g = g+x, max(f, g)
	}
	return max(f, g)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
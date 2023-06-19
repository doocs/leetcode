func rob(nums []int) int {
	f, g := 0, nums[0]
	for _, x := range nums[1:] {
		f, g = g, max(f+x, g)
	}
	return g
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
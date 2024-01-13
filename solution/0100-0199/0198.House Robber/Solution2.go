func rob(nums []int) int {
	f, g := 0, 0
	for _, x := range nums {
		f, g = max(f, g), f+x
	}
	return max(f, g)
}
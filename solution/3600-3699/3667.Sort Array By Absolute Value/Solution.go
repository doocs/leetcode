func sortByAbsoluteValue(nums []int) []int {
	slices.SortFunc(nums, func(a, b int) int {
		return abs(a) - abs(b)
	})
	return nums
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

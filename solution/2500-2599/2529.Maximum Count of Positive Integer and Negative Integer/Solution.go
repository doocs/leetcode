func maximumCount(nums []int) int {
	a := len(nums) - sort.SearchInts(nums, 1)
	b := sort.SearchInts(nums, 0)
	return max(a, b)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
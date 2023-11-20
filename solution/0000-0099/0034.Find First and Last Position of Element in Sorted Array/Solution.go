func searchRange(nums []int, target int) []int {
	l := sort.SearchInts(nums, target)
	r := sort.SearchInts(nums, target+1)
	if l == r {
		return []int{-1, -1}
	}
	return []int{l, r - 1}
}
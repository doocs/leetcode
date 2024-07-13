func search(nums []int, target int) int {
	l := sort.SearchInts(nums, target)
	r := sort.SearchInts(nums, target+1)
	return r - l
}
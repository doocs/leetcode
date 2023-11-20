func isMajorityElement(nums []int, target int) bool {
	left := sort.SearchInts(nums, target)
	right := sort.SearchInts(nums, target+1)
	return right-left > len(nums)/2
}
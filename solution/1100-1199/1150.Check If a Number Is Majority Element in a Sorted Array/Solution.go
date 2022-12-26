func isMajorityElement(nums []int, target int) bool {
	n := len(nums)
	left := sort.Search(n, func(i int) bool { return nums[i] >= target })
	right := sort.Search(n, func(i int) bool { return nums[i] > target })
	return right-left > n/2
}
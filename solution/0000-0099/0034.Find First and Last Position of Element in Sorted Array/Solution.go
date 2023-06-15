func searchRange(nums []int, target int) []int {
	l := sort.Search(len(nums), func(i int) bool { return nums[i] >= target })
	r := sort.Search(len(nums), func(i int) bool { return nums[i] > target })
	if l == r {
		return []int{-1, -1}
	}
	return []int{l, r - 1}
}
func search(nums []int, target int) int {
	l := sort.Search(len(nums), func(i int) bool { return nums[i] >= target })
	r := sort.Search(len(nums), func(i int) bool { return nums[i] > target })
	return r - l
}
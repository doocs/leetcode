func transformArray(nums []int) []int {
	even := 0
	for _, x := range nums {
		even += x&1 ^ 1
	}
	for i := 0; i < even; i++ {
		nums[i] = 0
	}
	for i := even; i < len(nums); i++ {
		nums[i] = 1
	}
	return nums
}

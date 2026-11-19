func minimizeSum(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	a := nums[n-3] - nums[0]
	b := nums[n-2] - nums[1]
	c := nums[n-1] - nums[2]
	return min(a, min(b, c))
}

func largestPerimeter(nums []int) int {
	sort.Ints(nums)
	for i := len(nums) - 1; i >= 2; i-- {
		c := nums[i-1] + nums[i-2]
		if c > nums[i] {
			return c + nums[i]
		}
	}
	return 0
}
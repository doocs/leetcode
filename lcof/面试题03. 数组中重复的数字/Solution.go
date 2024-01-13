func findRepeatNumber(nums []int) int {
	sort.Ints(nums)
	for i := 0; ; i++ {
		if nums[i] == nums[i+1] {
			return nums[i]
		}
	}
}
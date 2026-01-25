func minimumPrefixLength(nums []int) int {
	for i := len(nums) - 1; i > 0; i-- {
		if nums[i-1] >= nums[i] {
			return i
		}
	}
	return 0
}

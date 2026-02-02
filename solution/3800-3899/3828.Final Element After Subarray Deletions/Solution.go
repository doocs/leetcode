func finalElement(nums []int) int {
	return max(nums[0], nums[len(nums)-1])
}

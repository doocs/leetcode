func minOperations(nums []int) int {
	for _, x := range nums {
		if x != nums[0] {
			return 1
		}
	}
	return 0
}

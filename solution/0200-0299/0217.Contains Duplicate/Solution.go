func containsDuplicate(nums []int) bool {
	sort.Ints(nums)
	for i, v := range nums[1:] {
		if v == nums[i] {
			return true
		}
	}
	return false
}
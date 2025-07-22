func firstMissingPositive(nums []int) int {
	n := len(nums)
	for i := range nums {
		for 0 < nums[i] && nums[i] <= n && nums[i] != nums[nums[i]-1] {
			nums[i], nums[nums[i]-1] = nums[nums[i]-1], nums[i]
		}
	}
	for i, x := range nums {
		if x != i+1 {
			return i + 1
		}
	}
	return n + 1
}

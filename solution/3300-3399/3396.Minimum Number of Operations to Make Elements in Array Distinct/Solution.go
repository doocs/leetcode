func minimumOperations(nums []int) int {
	s := map[int]bool{}
	for i := len(nums) - 1; i >= 0; i-- {
		if s[nums[i]] {
			return i/3 + 1
		}
		s[nums[i]] = true
	}
	return 0
}

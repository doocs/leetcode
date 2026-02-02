func minOperations(nums []int, target []int) int {
	s := make(map[int]struct{})
	for i := 0; i < len(nums); i++ {
		if nums[i] != target[i] {
			s[nums[i]] = struct{}{}
		}
	}
	return len(s)
}

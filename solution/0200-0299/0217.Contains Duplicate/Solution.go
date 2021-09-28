func containsDuplicate(nums []int) bool {
	s := make(map[int]bool)
	for _, e := range nums {
		if s[e] {
			return true
		}
		s[e] = true
	}
	return false
}
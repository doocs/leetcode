func containsDuplicate(nums []int) bool {
	s := map[int]bool{}
	for _, v := range nums {
		if s[v] {
			return true
		}
		s[v] = true
	}
	return false
}
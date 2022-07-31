func minimumOperations(nums []int) int {
	s := map[int]bool{}
	for _, v := range nums {
		if v > 0 {
			s[v] = true
		}
	}
	return len(s)
}
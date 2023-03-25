func expectNumber(scores []int) int {
	s := map[int]struct{}{}
	for _, x := range scores {
		s[x] = struct{}{}
	}
	return len(s)
}
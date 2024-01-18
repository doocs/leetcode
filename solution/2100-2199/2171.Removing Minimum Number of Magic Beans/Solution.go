func minimumRemoval(beans []int) int64 {
	sort.Ints(beans)
	s := 0
	for _, x := range beans {
		s += x
	}
	ans := s
	n := len(beans)
	for i, x := range beans {
		ans = min(ans, s-x*(n-i))
	}
	return int64(ans)
}
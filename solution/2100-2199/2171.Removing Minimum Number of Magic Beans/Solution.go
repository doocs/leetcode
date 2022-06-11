func minimumRemoval(beans []int) int64 {
	sort.Ints(beans)
	s := 0
	for _, v := range beans {
		s += v
	}
	ans := s
	n := len(beans)
	for i, v := range beans {
		ans = min(ans, s-v*(n-i))
	}
	return int64(ans)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
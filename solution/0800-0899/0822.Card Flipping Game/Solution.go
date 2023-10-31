func flipgame(fronts []int, backs []int) int {
	s := map[int]struct{}{}
	for i, a := range fronts {
		if a == backs[i] {
			s[a] = struct{}{}
		}
	}
	ans := 9999
	for _, v := range fronts {
		if _, ok := s[v]; !ok {
			ans = min(ans, v)
		}
	}
	for _, v := range backs {
		if _, ok := s[v]; !ok {
			ans = min(ans, v)
		}
	}
	return ans % 9999
}
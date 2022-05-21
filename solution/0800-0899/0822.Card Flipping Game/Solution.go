func flipgame(fronts []int, backs []int) int {
	s := map[int]bool{}
	for i, v := range fronts {
		if v == backs[i] {
			s[v] = true
		}
	}
	ans := 9999
	for _, v := range fronts {
		if !s[v] {
			ans = min(ans, v)
		}
	}
	for _, v := range backs {
		if !s[v] {
			ans = min(ans, v)
		}
	}
	return ans % 9999
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
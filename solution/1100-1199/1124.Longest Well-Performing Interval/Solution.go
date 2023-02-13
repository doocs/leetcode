func longestWPI(hours []int) (ans int) {
	s := 0
	pos := map[int]int{}
	for i, x := range hours {
		if x > 8 {
			s++
		} else {
			s--
		}
		if s > 0 {
			ans = i + 1
		} else if j, ok := pos[s-1]; ok {
			ans = max(ans, i-j)
		}
		if _, ok := pos[s]; !ok {
			pos[s] = i
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
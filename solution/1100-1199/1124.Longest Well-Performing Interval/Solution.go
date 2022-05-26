func longestWPI(hours []int) int {
	s, ans := 0, 0
	seen := make(map[int]int)
	for i, h := range hours {
		if h > 8 {
			s += 1
		} else {
			s -= 1
		}
		if s > 0 {
			ans = i + 1
		} else {
			if _, ok := seen[s]; !ok {
				seen[s] = i
			}
			if j, ok := seen[s-1]; ok {
				ans = max(ans, i-j)
			}
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
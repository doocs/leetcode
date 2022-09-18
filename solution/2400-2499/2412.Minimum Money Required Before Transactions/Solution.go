func minimumMoney(transactions [][]int) int64 {
	s, ans := 0, 0
	for _, e := range transactions {
		s += max(0, e[0]-e[1])
	}
	for _, e := range transactions {
		if e[0] > e[1] {
			ans = max(ans, s+e[1])
		} else {
			ans = max(ans, s+e[0])
		}
	}
	return int64(ans)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
func minimumCardPickup(cards []int) int {
	m := map[int]int{}
	ans := 1000000
	for i, c := range cards {
		if j, ok := m[c]; ok {
			ans = min(ans, i-j+1)
		}
		m[c] = i
	}
	if ans == 1000000 {
		return -1
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
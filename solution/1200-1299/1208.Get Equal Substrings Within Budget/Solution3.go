func equalSubstring(s string, t string, maxCost int) int {
	n := len(s)
	var cost, l int
	for r := range s {
		cost += abs(int(s[r]) - int(t[r]))
		if cost > maxCost {
			cost -= abs(int(s[l]) - int(t[l]))
			l++
		}
	}
	return n - l
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func equalSubstring(s string, t string, maxCost int) (ans int) {
	var cost, l int
	for r := range s {
		cost += abs(int(s[r]) - int(t[r]))
		for ; cost > maxCost; l++ {
			cost -= abs(int(s[l]) - int(t[l]))
		}
		ans = max(ans, r-l+1)
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

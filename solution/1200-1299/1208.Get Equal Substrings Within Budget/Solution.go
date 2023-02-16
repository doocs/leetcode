func equalSubstring(s string, t string, maxCost int) (ans int) {
	var sum, j int
	for i := range s {
		sum += abs(int(s[i]) - int(t[i]))
		for ; sum > maxCost; j++ {
			sum -= abs(int(s[j]) - int(t[j]))
		}
		if ans < i-j+1 {
			ans = i - j + 1
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
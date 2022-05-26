func maxPower(s string) int {
	ans, t := 0, 0
	for i := range s {
		if i == 0 || s[i] == s[i-1] {
			t++
		} else {
			t = 1
		}
		ans = max(ans, t)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
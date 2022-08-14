func maxScore(s string) int {
	t := 0
	if s[0] == '0' {
		t++
	}
	n := len(s)
	for i := 1; i < n; i++ {
		if s[i] == '1' {
			t++
		}
	}
	ans := t
	for i := 1; i < n-1; i++ {
		if s[i] == '0' {
			t++
		} else {
			t--
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
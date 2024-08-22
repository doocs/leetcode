func maxScore(s string) (ans int) {
	l, r := 0, strings.Count(s, "1")
	for _, c := range s[:len(s)-1] {
		if c == '0' {
			l++
		} else {
			r--
		}
		ans = max(ans, l+r)
	}
	return
}

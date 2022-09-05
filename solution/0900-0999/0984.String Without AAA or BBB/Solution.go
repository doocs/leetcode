func strWithout3a3b(a int, b int) string {
	var ans strings.Builder
	for a > 0 && b > 0 {
		if a > b {
			ans.WriteString("aab")
			a -= 2
			b -= 1
		} else if a < b {
			ans.WriteString("bba")
			a -= 1
			b -= 2
		} else {
			ans.WriteString("ab")
			a--
			b--
		}
	}
	if a > 0 {
		ans.WriteString(strings.Repeat("a", a))
	}
	if b > 0 {
		ans.WriteString(strings.Repeat("b", b))
	}
	return ans.String()
}
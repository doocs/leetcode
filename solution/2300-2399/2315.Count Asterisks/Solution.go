func countAsterisks(s string) (ans int) {
	ok := 1
	for _, c := range s {
		if c == '*' {
			ans += ok
		} else if c == '|' {
			ok ^= 1
		}
	}
	return
}
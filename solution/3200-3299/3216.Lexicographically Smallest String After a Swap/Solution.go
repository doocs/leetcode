func getSmallestString(s string) string {
	cs := []byte(s)
	n := len(cs)
	for i := 1; i < n; i++ {
		a, b := cs[i-1], cs[i]
		if a > b && a%2 == b%2 {
			cs[i-1], cs[i] = b, a
			return string(cs)
		}
	}
	return s
}
func findContestMatch(n int) string {
	s := make([]string, n)
	for i := 0; i < n; i++ {
		s[i] = strconv.Itoa(i + 1)
	}
	for ; n > 1; n >>= 1 {
		for i := 0; i < n>>1; i++ {
			s[i] = fmt.Sprintf("(%s,%s)", s[i], s[n-i-1])
		}
	}
	return s[0]
}
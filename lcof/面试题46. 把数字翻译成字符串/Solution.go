func translateNum(num int) int {
	s := strconv.Itoa(num)
	n := len(s)
	a, b := 1, 1
	for i := 1; i < n; i++ {
		c := b
		if s[i-1] == '1' || (s[i-1] == '2' && s[i] < '6') {
			c += a
		}
		a, b = b, c
	}
	return b
}
func decodeAtIndex(s string, k int) string {
	m := 0
	for _, c := range s {
		if c >= '0' && c <= '9' {
			m *= int(c - '0')
		} else {
			m++
		}
	}
	for i := len(s) - 1; ; i-- {
		k %= m
		if k == 0 && s[i] >= 'a' && s[i] <= 'z' {
			return string(s[i])
		}
		if s[i] >= '0' && s[i] <= '9' {
			m /= int(s[i] - '0')
		} else {
			m--
		}
	}
}
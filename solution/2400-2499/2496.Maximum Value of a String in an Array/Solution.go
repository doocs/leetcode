func maximumValue(strs []string) (ans int) {
	f := func(s string) int {
		n, m := len(s), 0
		for _, c := range s {
			if c >= 'a' && c <= 'z' {
				return n
			}
			m = m*10 + int(c-'0')
		}
		return m
	}
	for _, s := range strs {
		if t := f(s); ans < t {
			ans = t
		}
	}
	return
}
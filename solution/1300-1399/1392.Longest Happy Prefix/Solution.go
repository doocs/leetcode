func longestPrefix(s string) string {
	base := 131
	n := 100010
	p := make([]int, n)
	h := make([]int, n)
	p[0] = 1
	for i := 1; i <= len(s); i++ {
		p[i] = p[i-1] * base
		h[i] = h[i-1]*base + int(s[i-1])
	}
	l := len(s)
	for i := l - 1; i > 0; i-- {
		prefix := h[i]
		suffix := h[l] - h[l-i]*p[i]
		if prefix == suffix {
			return s[:i]
		}
	}
	return ""
}
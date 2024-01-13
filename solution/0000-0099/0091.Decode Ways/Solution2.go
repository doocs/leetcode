func numDecodings(s string) int {
	n := len(s)
	f, g := 0, 1
	for i := 1; i <= n; i++ {
		h := 0
		if s[i-1] != '0' {
			h = g
		}
		if i > 1 && (s[i-2] == '1' || (s[i-2] == '2' && s[i-1] <= '6')) {
			h += f
		}
		f, g = g, h
	}
	return g
}
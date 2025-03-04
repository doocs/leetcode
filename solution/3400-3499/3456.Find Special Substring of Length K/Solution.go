func hasSpecialSubstring(s string, k int) bool {
	n := len(s)
	for l := 0; l < n; {
		r := l + 1
		for r < n && s[r] == s[l] {
			r++
		}
		if r-l == k {
			return true
		}
		l = r
	}
	return false
}

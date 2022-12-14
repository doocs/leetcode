func longestNiceSubstring(s string) string {
	n := len(s)
	k, mx := -1, 0
	for i := 0; i < n; i++ {
		var lower, upper int
		for j := i; j < n; j++ {
			if unicode.IsLower(rune(s[j])) {
				lower |= 1 << (s[j] - 'a')
			} else {
				upper |= 1 << (s[j] - 'A')
			}
			if lower == upper && mx < j-i+1 {
				mx = j - i + 1
				k = i
			}
		}
	}
	if k < 0 {
		return ""
	}
	return s[k : k+mx]
}
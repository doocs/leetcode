func isCircularSentence(s string) bool {
	n := len(s)
	if s[0] != s[n-1] {
		return false
	}
	for i := 1; i < n; i++ {
		if s[i] == ' ' && s[i-1] != s[i+1] {
			return false
		}
	}
	return true
}
func firstMatchingIndex(s string) int {
	n := len(s)
	for i := 0; i < n/2+1; i++ {
		if s[i] == s[n-i-1] {
			return i
		}
	}
	return -1
}

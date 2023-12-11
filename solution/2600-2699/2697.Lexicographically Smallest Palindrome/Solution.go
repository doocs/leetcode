func makeSmallestPalindrome(s string) string {
	cs := []byte(s)
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		cs[i] = min(cs[i], cs[j])
		cs[j] = cs[i]
	}
	return string(cs)
}
func makeSmallestPalindrome(s string) string {
	cs := []byte(s)
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		if cs[i] != cs[j] {
			if cs[i] < cs[j] {
				cs[j] = cs[i]
			} else {
				cs[i] = cs[j]
			}
		}
	}
	return string(cs)
}
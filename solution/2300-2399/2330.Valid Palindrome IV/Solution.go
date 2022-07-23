func makePalindrome(s string) bool {
	t := 0
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		if s[i] != s[j] {
			t++
		}
	}
	return t <= 2
}
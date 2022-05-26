func firstPalindrome(words []string) string {
	check := func(s string) bool {
		for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
			if s[i] != s[j] {
				return false
			}
		}
		return true
	}

	for _, word := range words {
		if check(word) {
			return word
		}
	}
	return ""
}
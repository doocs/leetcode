func isPalindrome(s string) bool {
	s = strings.ToLower(s)
	left, right := 0, len(s) - 1
	for left < right {
		for left < right && !verify(s[left]) {
			left++
		}
		for left < right && !verify(s[right]) {
			right--
		}
		if left < right {
			if s[left] != s[right] {
				return false
			}
			left++
			right--
		}
	}
	return true
}

func verify(ch byte) bool {
	return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')
}
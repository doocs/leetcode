func isPalindrome(s string) bool {
	i, j := 0, len(s)-1
	for i < j {
		if !isalnum(s[i]) {
			i++
		} else if !isalnum(s[j]) {
			j--
		} else if tolower(s[i]) != tolower(s[j]) {
			return false
		} else {
			i, j = i+1, j-1
		}
	}
	return true
}

func isalnum(ch byte) bool {
	return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')
}

func tolower(ch byte) byte {
	if ch >= 'A' && ch <= 'Z' {
		return ch + 32
	}
	return ch
}
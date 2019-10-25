func lengthOfLastWord(s string) int {
	if len(s) == 0 {
		return 0
	}
	space := []byte(" ")[0]
	for len(s) != 0 && s[len(s)-1] == space {
		s = s[:len(s)-1]
	}
	ret := 0
	for i := len(s) - 1; i >= 0; i-- {
		if s[i] != space {
			ret++
		} else {
			return ret
		}
	}
	return ret
}

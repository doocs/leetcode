func countValidWords(sentence string) (ans int) {
	check := func(s string) int {
		if len(s) == 0 {
			return 0
		}
		st := false
		for i, r := range s {
			if unicode.IsDigit(r) {
				return 0
			}
			if (r == '!' || r == '.' || r == ',') && i < len(s)-1 {
				return 0
			}
			if r == '-' {
				if st || i == 0 || i == len(s)-1 {
					return 0
				}
				if !unicode.IsLetter(rune(s[i-1])) || !unicode.IsLetter(rune(s[i+1])) {
					return 0
				}
				st = true
			}
		}
		return 1
	}
	for _, s := range strings.Fields(sentence) {
		ans += check(s)
	}
	return ans
}

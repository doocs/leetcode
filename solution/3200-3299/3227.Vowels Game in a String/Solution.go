func doesAliceWin(s string) bool {
	vowels := "aeiou"
	for _, c := range s {
		if strings.ContainsRune(vowels, c) {
			return true
		}
	}
	return false
}
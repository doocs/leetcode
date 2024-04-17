func reverseOnlyLetters(s string) string {
	cs := []rune(s)
	i, j := 0, len(s)-1
	for i < j {
		for i < j && !unicode.IsLetter(cs[i]) {
			i++
		}
		for i < j && !unicode.IsLetter(cs[j]) {
			j--
		}
		if i < j {
			cs[i], cs[j] = cs[j], cs[i]
			i++
			j--
		}
	}
	return string(cs)
}
func vowelConsonantScore(s string) int {
	v, c := 0, 0
	for _, ch := range s {
		if unicode.IsLetter(ch) {
			c++
			if strings.ContainsRune("aeiou", ch) {
				v++
			}
		}
	}
	c -= v
	if c == 0 {
		return 0
	}
	return v / c
}

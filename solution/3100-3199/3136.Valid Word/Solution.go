func isValid(word string) bool {
	if len(word) < 3 {
		return false
	}
	hasVowel := false
	hasConsonant := false
	vs := make([]bool, 26)
	for _, c := range "aeiou" {
		vs[c-'a'] = true
	}
	for _, c := range word {
		if unicode.IsLetter(c) {
			if vs[unicode.ToLower(c)-'a'] {
				hasVowel = true
			} else {
				hasConsonant = true
			}
		} else if !unicode.IsDigit(c) {
			return false
		}
	}
	return hasVowel && hasConsonant
}
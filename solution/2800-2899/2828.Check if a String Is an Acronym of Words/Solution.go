func isAcronym(words []string, s string) bool {
	if len(words) != len(s) {
		return false
	}
	for i := range s {
		if words[i][0] != s[i] {
			return false
		}
	}
	return true
}
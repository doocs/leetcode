func trimTrailingVowels(s string) string {
	i := len(s) - 1
	for i >= 0 && strings.IndexByte("aeiou", s[i]) != -1 {
		i--
	}
	return s[:i+1]
}

func isPrefixString(s string, words []string) bool {
	t := ""
	for _, w := range words {
		t += w
		if t == s {
			return true
		}
	}
	return false
}
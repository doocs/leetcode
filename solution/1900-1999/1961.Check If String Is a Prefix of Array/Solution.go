func isPrefixString(s string, words []string) bool {
	t := strings.Builder{}
	for _, w := range words {
		t.WriteString(w)
		if t.Len() > len(s) {
			return false
		}
		if t.Len() == len(s) {
			return t.String() == s
		}
	}
	return false
}
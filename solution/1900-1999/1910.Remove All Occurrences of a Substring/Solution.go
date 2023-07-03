func removeOccurrences(s string, part string) string {
	for strings.Contains(s, part) {
		s = strings.Replace(s, part, "", 1)
	}
	return s
}
func countPrefixes(words []string, s string) (ans int) {
	for _, w := range words {
		if strings.HasPrefix(s, w) {
			ans++
		}
	}
	return
}
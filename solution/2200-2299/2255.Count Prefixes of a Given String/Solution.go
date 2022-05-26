func countPrefixes(words []string, s string) int {
	ans := 0
	for _, word := range words {
		if strings.HasPrefix(s, word) {
			ans++
		}
	}
	return ans
}
func prefixCount(words []string, pref string) int {
	ans := 0
	for _, w := range words {
		if strings.HasPrefix(w, pref) {
			ans++
		}
	}
	return ans
}
func prefixCount(words []string, pref string) (ans int) {
	for _, w := range words {
		if strings.HasPrefix(w, pref) {
			ans++
		}
	}
	return
}
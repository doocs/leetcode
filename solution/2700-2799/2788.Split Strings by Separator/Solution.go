func splitWordsBySeparator(words []string, separator byte) (ans []string) {
	for _, w := range words {
		for _, s := range strings.Split(w, string(separator)) {
			if s != "" {
				ans = append(ans, s)
			}
		}
	}
	return
}
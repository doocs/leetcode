func sortSentence(s string) string {
	words := strings.Split(s, " ")
	ans := make([]string, len(words))
	for _, w := range words {
		i := w[len(w)-1] - '1'
		ans[i] = w[:len(w)-1]
	}
	return strings.Join(ans, " ")
}
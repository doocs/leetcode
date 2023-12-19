func sortSentence(s string) string {
	ws := strings.Split(s, " ")
	ans := make([]string, len(ws))
	for _, w := range ws {
		ans[w[len(w)-1]-'1'] = w[:len(w)-1]
	}
	return strings.Join(ans, " ")
}
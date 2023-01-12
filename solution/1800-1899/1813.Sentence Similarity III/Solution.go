func areSentencesSimilar(sentence1 string, sentence2 string) bool {
	words1, words2 := strings.Fields(sentence1), strings.Fields(sentence2)
	if len(words1) < len(words2) {
		words1, words2 = words2, words1
	}
	m, n := len(words1), len(words2)
	i, j := 0, 0
	for i < n && words1[i] == words2[i] {
		i++
	}
	for j < n && words1[m-1-j] == words2[n-1-j] {
		j++
	}
	return i+j >= n
}
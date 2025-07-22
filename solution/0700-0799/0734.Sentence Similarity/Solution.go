func areSentencesSimilar(sentence1 []string, sentence2 []string, similarPairs [][]string) bool {
	if len(sentence1) != len(sentence2) {
		return false
	}
	s := map[string]bool{}
	for _, p := range similarPairs {
		s[p[0]+"#"+p[1]] = true
	}
	for i, x := range sentence1 {
		y := sentence2[i]
		if x != y && !s[x+"#"+y] && !s[y+"#"+x] {
			return false
		}
	}
	return true
}

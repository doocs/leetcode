func areSentencesSimilar(sentence1 []string, sentence2 []string, similarPairs [][]string) bool {
	if len(sentence1) != len(sentence2) {
		return false
	}
	s := map[string]bool{}
	for _, e := range similarPairs {
		s[e[0]+"."+e[1]] = true
	}
	for i, a := range sentence1 {
		b := sentence2[i]
		if a != b && !s[a+"."+b] && !s[b+"."+a] {
			return false
		}
	}
	return true
}
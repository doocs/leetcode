func areSentencesSimilar(sentence1 string, sentence2 string) bool {
	if sentence1 == sentence2 {
		return true
	}
	l1, l2 := len(sentence1), len(sentence2)
	if l1 == l2 {
		return false
	}
	if l1 < l2 {
		sentence1, sentence2 = sentence2, sentence1
	}
	i, j := 0, 0
	w1, w2 := strings.Fields(sentence1), strings.Fields(sentence2)
	l1, l2 = len(w1), len(w2)
	for i < l2 && w1[i] == w2[i] {
		i++
	}
	if i == l2 {
		return true
	}
	for j < l2 && w1[l1-1-j] == w2[l2-1-j] {
		j++
	}
	return j == l2 || i+j == l2
}

func replaceWords(dictionary []string, sentence string) string {
	s := map[string]bool{}
	for _, v := range dictionary {
		s[v] = true
	}
	words := strings.Split(sentence, " ")
	for i, word := range words {
		for j := 1; j <= len(word); j++ {
			t := word[:j]
			if s[t] {
				words[i] = t
				break
			}
		}
	}
	return strings.Join(words, " ")
}
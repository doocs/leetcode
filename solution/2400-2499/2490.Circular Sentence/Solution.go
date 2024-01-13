func isCircularSentence(sentence string) bool {
	ss := strings.Split(sentence, " ")
	n := len(ss)
	for i, s := range ss {
		if s[len(s)-1] != ss[(i+1)%n][0] {
			return false
		}
	}
	return true
}
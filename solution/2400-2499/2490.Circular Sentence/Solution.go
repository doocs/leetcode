func isCircularSentence(sentence string) bool {
	if sentence[0] != sentence[len(sentence)-1] {
		return false
	}
	ss := strings.Split(sentence, " ")
	for i := 1; i < len(ss); i++ {
		if ss[i][0] != ss[i-1][len(ss[i-1])-1] {
			return false
		}
	}
	return true
}
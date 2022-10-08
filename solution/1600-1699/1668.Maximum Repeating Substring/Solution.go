func maxRepeating(sequence string, word string) int {
	x := len(sequence) / len(word)
	for k := x; k > 0; k-- {
		if strings.Contains(sequence, strings.Repeat(word, k)) {
			return k
		}
	}
	return 0
}
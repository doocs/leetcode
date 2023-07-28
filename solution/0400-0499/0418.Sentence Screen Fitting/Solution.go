func wordsTyping(sentence []string, rows int, cols int) int {
	s := strings.Join(sentence, " ") + " "
	m := len(s)
	cur := 0
	for i := 0; i < rows; i++ {
		cur += cols
		if s[cur%m] == ' ' {
			cur++
		} else {
			for cur > 0 && s[(cur-1)%m] != ' ' {
				cur--
			}
		}
	}
	return cur / m
}
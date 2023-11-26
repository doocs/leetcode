func findWordsContaining(words []string, x byte) (ans []int) {
	for i, w := range words {
		for _, c := range w {
			if byte(c) == x {
				ans = append(ans, i)
				break
			}
		}
	}
	return
}
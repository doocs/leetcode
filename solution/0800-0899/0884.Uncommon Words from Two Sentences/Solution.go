func uncommonFromSentences(s1 string, s2 string) []string {
	counter := make(map[string]int)
	add := func(s string) {
		for _, w := range strings.Split(s, " ") {
			counter[w]++
		}
	}
	add(s1)
	add(s2)
	var ans []string
	for word, n := range counter {
		if n == 1 {
			ans = append(ans, word)
		}
	}
	return ans
}
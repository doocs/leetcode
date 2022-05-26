func findWords(words []string) []string {
	s := "12210111011122000010020202"
	var ans []string
	for _, word := range words {
		t := make(map[byte]bool)
		for _, c := range word {
			t[s[unicode.ToLower(c)-'a']] = true
		}
		if len(t) == 1 {
			ans = append(ans, word)
		}
	}
	return ans
}
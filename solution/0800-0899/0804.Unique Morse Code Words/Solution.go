func uniqueMorseRepresentations(words []string) int {
	codes := []string{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.",
		"---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."}
	s := make(map[string]bool)
	for _, word := range words {
		t := &strings.Builder{}
		for _, c := range word {
			t.WriteString(codes[c-'a'])
		}
		s[t.String()] = true
	}
	return len(s)
}
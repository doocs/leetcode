func reverseWords(s string) string {
	words := strings.Fields(s)
	for i, w := range words {
		t := []byte(w)
		slices.Reverse(t)
		words[i] = string(t)
	}
	return strings.Join(words, " ")
}

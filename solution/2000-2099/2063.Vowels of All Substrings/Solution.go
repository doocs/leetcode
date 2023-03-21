func countVowels(word string) (ans int64) {
	for i, c := range word {
		if c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' {
			ans += int64((i + 1) * (len(word) - i))
		}
	}
	return
}
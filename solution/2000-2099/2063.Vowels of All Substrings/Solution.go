func countVowels(word string) int64 {
	var ans int64
	n := len(word)
	for i, c := range word {
		if c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' {
			ans += int64((i + 1) * (n - i))
		}
	}
	return ans
}
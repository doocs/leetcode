func makeEqual(words []string) bool {
	counter := [26]int{}
	for _, word := range words {
		for _, c := range word {
			counter[c-'a']++
		}
	}
	n := len(words)
	for _, count := range counter {
		if count%n != 0 {
			return false
		}
	}
	return true
}
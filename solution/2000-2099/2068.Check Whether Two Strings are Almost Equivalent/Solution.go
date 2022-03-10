func checkAlmostEquivalent(word1 string, word2 string) bool {
	counter := make([]int, 26)
	for i := range word1 {
		counter[word1[i]-'a']++
	}
	for i := range word2 {
		counter[word2[i]-'a']--
	}
	for _, v := range counter {
		if v > 3 || -v > 3 {
			return false
		}
	}
	return true
}
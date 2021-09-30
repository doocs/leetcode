func numOfStrings(patterns []string, word string) int {
    res := 0
    for _, p := range patterns {
		if strings.Contains(word, p) {
			res++
		}
	}
    return res
}
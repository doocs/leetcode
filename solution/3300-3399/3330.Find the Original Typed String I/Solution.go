func possibleStringCount(word string) int {
	f := 1
	for i := 1; i < len(word); i++ {
		if word[i] == word[i-1] {
			f++
		}
	}
	return f
}

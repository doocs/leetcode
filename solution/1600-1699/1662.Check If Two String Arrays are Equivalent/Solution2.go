func arrayStringsAreEqual(word1 []string, word2 []string) bool {
	i1, j1, i2, j2 := 0, 0, 0, 0
	for i1 < len(word1) && i2 < len(word2) {
		if word1[i1][j1] != word2[i2][j2] {
			return false
		}
		j1++
		if j1 == len(word1[i1]) {
			i1, j1 = i1+1, 0
		}
		j2++
		if j2 == len(word2[i2]) {
			i2, j2 = i2+1, 0
		}
	}
	return i1 == len(word1) && i2 == len(word2)
}

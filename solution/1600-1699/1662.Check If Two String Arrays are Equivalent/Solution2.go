func arrayStringsAreEqual(word1 []string, word2 []string) bool {
	var i, j, x, y int
	for i < len(word1) && j < len(word2) {
		if word1[i][x] != word2[j][y] {
			return false
		}
		x, y = x+1, y+1
		if x == len(word1[i]) {
			x, i = 0, i+1
		}
		if y == len(word2[j]) {
			y, j = 0, j+1
		}
	}
	return i == len(word1) && j == len(word2)
}
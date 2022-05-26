func findClosest(words []string, word1 string, word2 string) int {
	idx1, idx2, ans := 100000, -100000, 100000
	for i, word := range words {
		if word == word1 {
			idx1 = i
		} else if word == word2 {
			idx2 = i
		}
		ans = min(ans, abs(idx1-idx2))
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
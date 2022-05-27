func findClosest(words []string, word1 string, word2 string) int {
	i, j, ans := 100000, -100000, 100000
	for k, word := range words {
		if word == word1 {
			i = k
		} else if word == word2 {
			j = k
		}
		ans = min(ans, abs(i-j))
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
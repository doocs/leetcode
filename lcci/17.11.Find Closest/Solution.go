func findClosest(words []string, word1 string, word2 string) int {
	const inf int = 1 << 29
	i, j, ans := inf, -inf, inf
	for k, w := range words {
		if w == word1 {
			i = k
		} else if w == word2 {
			j = k
		}
		ans = min(ans, max(i-j, j-i))
	}
	return ans
}
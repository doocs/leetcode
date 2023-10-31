func longestValidSubstring(word string, forbidden []string) (ans int) {
	s := map[string]bool{}
	for _, x := range forbidden {
		s[x] = true
	}
	n := len(word)
	for i, j := 0, 0; j < n; j++ {
		for k := j; k > max(j-10, i-1); k-- {
			if s[word[k:j+1]] {
				i = k + 1
				break
			}
		}
		ans = max(ans, j-i+1)
	}
	return
}
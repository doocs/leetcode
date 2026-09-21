func indexPairs(text string, words []string) (ans [][]int) {
	s := map[string]bool{}
	for _, w := range words {
		s[w] = true
	}
	n := len(text)
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			if s[text[i:j+1]] {
				ans = append(ans, []int{i, j})
			}
		}
	}
	return
}

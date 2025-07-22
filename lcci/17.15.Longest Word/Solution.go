func longestWord(words []string) string {
	s := map[string]bool{}
	for _, w := range words {
		s[w] = true
	}
	sort.Slice(words, func(i, j int) bool {
		return len(words[i]) > len(words[j]) || (len(words[i]) == len(words[j]) && words[i] < words[j])
	})
	var dfs func(string) bool
	dfs = func(w string) bool {
		if len(w) == 0 {
			return true
		}
		for k := 1; k <= len(w); k++ {
			if s[w[:k]] && dfs(w[k:]) {
				return true
			}
		}
		return false
	}
	for _, w := range words {
		s[w] = false
		if dfs(w) {
			return w
		}
	}
	return ""
}

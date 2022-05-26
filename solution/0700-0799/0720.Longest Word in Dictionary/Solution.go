func longestWord(words []string) string {
	s := make(map[string]bool)
	for _, w := range words {
		s[w] = true
	}
	cnt := 0
	ans := ""
	check := func(word string) bool {
		for i, n := 1, len(word); i < n; i++ {
			if !s[word[:i]] {
				return false
			}
		}
		return true
	}
	for w, _ := range s {
		n := len(w)
		if check(w) {
			if cnt < n {
				cnt, ans = n, w
			} else if cnt == n && w < ans {
				ans = w
			}
		}
	}
	return ans
}
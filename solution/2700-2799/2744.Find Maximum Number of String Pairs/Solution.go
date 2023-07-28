func maximumNumberOfStringPairs(words []string) (ans int) {
	cnt := map[string]int{}
	for _, w := range words {
		ans += cnt[w]
		s := []byte(w)
		for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
			s[i], s[j] = s[j], s[i]
		}
		cnt[string(s)]++
	}
	return
}
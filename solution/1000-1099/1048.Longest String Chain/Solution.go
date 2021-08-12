func longestStrChain(words []string) int {
	sort.Slice(words, func(i, j int) bool { return len(words[i]) < len(words[j]) })
	res := 0
	mp := make(map[string]int)
	for _, word := range words {
		x := 1
		for i := 0; i < len(word); i++ {
			pre := word[0:i] + word[i+1:len(word)]
			x = max(x, mp[pre]+1)
		}
		mp[word] = x
		res = max(res, x)
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
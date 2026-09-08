func longestStrChain(words []string) int {
	sort.Slice(words, func(i, j int) bool { return len(words[i]) < len(words[j]) })
	f := map[string]int{}
	ans := 0
	for _, w := range words {
		x := 1
		for i := range w {
			pred := w[:i] + w[i+1:]
			x = max(x, f[pred]+1)
		}
		f[w] = x
		ans = max(ans, x)
	}
	return ans
}

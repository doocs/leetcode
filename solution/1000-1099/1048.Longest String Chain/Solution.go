func longestStrChain(words []string) int {
	sort.Slice(words, func(i, j int) bool { return len(words[i]) < len(words[j]) })
	n := len(words)
	f := make([]int, n)
	ans := 0
	for i := 0; i < n; i++ {
		f[i] = 1
		for j := 0; j < i; j++ {
			if check(words[j], words[i]) {
				f[i] = max(f[i], f[j]+1)
			}
		}
		ans = max(ans, f[i])
	}
	return ans
}

func check(a, b string) bool {
	if len(a)+1 != len(b) {
		return false
	}
	i := 0
	for j := range b {
		if i < len(a) && a[i] == b[j] {
			i++
		}
	}
	return i == len(a)
}

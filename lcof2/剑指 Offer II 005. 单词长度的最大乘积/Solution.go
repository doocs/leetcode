func maxProduct(words []string) (ans int) {
	n := len(words)
	mask := make([]int, n)
	for i, w := range words {
		for _, c := range w {
			mask[i] |= 1 << (c - 'a')
		}
	}
	for i, x := range mask {
		for j := i + 1; j < n; j++ {
			if x&mask[j] == 0 {
				ans = max(ans, len(words[i])*len(words[j]))
			}
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
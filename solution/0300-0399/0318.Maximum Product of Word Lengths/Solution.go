func maxProduct(words []string) (ans int) {
	n := len(words)
	mask := make([]int, n)
	for i, s := range words {
		for _, c := range s {
			mask[i] |= 1 << (c - 'a')
		}
		for j, t := range words[:i] {
			if mask[i]&mask[j] == 0 {
				ans = max(ans, len(s)*len(t))
			}
		}
	}
	return
}
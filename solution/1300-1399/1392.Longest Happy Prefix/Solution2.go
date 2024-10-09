func longestPrefix(s string) string {
	s += "#"
	n := len(s)
	next := make([]int, n)
	next[0], next[1] = -1, 0
	for i, j := 2, 0; i < n; {
		if s[i-1] == s[j] {
			j++
			next[i] = j
			i++
		} else if j > 0 {
			j = next[j]
		} else {
			next[i] = 0
			i++
		}
	}
	return s[:next[n-1]]
}

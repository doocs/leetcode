func appendCharacters(s string, t string) int {
	m, n := len(s), len(t)
	for i, j := 0, 0; j < n; i, j = i+1, j+1 {
		for i < m && s[i] != t[j] {
			i++
		}
		if i == m {
			return n - j
		}
	}
	return 0
}
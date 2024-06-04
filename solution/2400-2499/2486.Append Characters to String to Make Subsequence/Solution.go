func appendCharacters(s string, t string) int {
	n, j := len(t), 0
	for _, c := range s {
		if j < n && byte(c) == t[j] {
			j++
		}
	}
	return n - j
}
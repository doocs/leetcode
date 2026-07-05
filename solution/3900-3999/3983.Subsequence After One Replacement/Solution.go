func canMakeSubsequence(s string, t string) bool {
	m, n := len(s), len(t)
	i0, i1, j := 0, 0, 0

	for i1 < m && j < n {
		if s[i1] == t[j] {
			i1++
		}

		if i1 < i0+1 {
			i1 = i0 + 1
		}

		if s[i0] == t[j] {
			i0++
		}

		j++
	}

	return i1 == m
}

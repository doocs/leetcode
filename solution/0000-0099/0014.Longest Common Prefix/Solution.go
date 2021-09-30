func longestCommonPrefix(strs []string) string {
	if len(strs) == 0 {
		return ""
	}

	var b strings.Builder
	m, n := len(strs[0]), len(strs)

LOOP:
	for i := 0; i < m; i++ {
		for j := 1; j < n; j++ {
			if i >= len(strs[j]) || strs[0][i] != strs[j][i] {
				break LOOP
			}
		}
		b.WriteByte(strs[0][i])
	}

	return b.String()
}

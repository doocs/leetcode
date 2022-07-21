func countDistinct(s string) int {
	ss := map[string]bool{}
	for i := range s {
		for j := i + 1; j <= len(s); j++ {
			ss[s[i:j]] = true
		}
	}
	return len(ss)
}
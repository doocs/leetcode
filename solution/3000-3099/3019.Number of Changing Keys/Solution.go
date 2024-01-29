func countKeyChanges(s string) int {
	n := len(s)
	count := 0
	s = strings.ToLower(s)
	for i := 0; i < n-1; i++ {
		if s[i] != s[i+1] {
			count++
		}
	}
	return count
}

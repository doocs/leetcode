func hasAllCodes(s string, k int) bool {
	ss := map[string]bool{}
	for i := 0; i+k <= len(s); i++ {
		ss[s[i:i+k]] = true
	}
	return len(ss) == 1<<k
}
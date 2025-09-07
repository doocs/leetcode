func minOperations(s string) (ans int) {
	for _, c := range s {
		if c != 'a' {
			ans = max(ans, 26-int(c-'a'))
		}
	}
	return
}

func reverseStr(s string, k int) string {
	t := []byte(s)
	for i := 0; i < len(t); i += (k << 1) {
		for st, ed := i, min(i+k-1, len(t)-1); st < ed; st, ed = st+1, ed-1 {
			t[st], t[ed] = t[ed], t[st]
		}
	}
	return string(t)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
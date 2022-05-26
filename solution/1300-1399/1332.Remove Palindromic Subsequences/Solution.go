func removePalindromeSub(s string) int {
	if len(s) == 0 {
		return 0
	}
	if s == reverse(s) {
		return 1
	}
	return 2
}

func reverse(s string) string {
	r := []byte(s)
	for i, j := 0, len(r)-1; i < j; i, j = i+1, j-1 {
		r[i], r[j] = r[j], r[i]
	}
	return string(r)
}
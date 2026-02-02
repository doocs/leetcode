func reversePrefix(s string, k int) string {
	t := []byte(s[:k])
	slices.Reverse(t)
	return string(t) + s[k:]
}

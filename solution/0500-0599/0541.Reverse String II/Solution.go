func reverseStr(s string, k int) string {
	cs := []byte(s)
	n := len(cs)
	for i := 0; i < n; i += 2 * k {
		for l, r := i, min(i+k-1, n-1); l < r; l, r = l+1, r-1 {
			cs[l], cs[r] = cs[r], cs[l]
		}
	}
	return string(cs)
}
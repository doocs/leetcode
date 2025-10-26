func lexSmallest(s string) string {
	ans := s
	n := len(s)
	for k := 1; k <= n; k++ {
		t1r := []rune(s[:k])
		slices.Reverse(t1r)
		t1 := string(t1r) + s[k:]

		t2r := []rune(s[n-k:])
		slices.Reverse(t2r)
		t2 := s[:n-k] + string(t2r)

		ans = min(ans, t1, t2)
	}
	return ans
}

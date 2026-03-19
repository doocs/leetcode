func almostPalindromic(s string) int {
	n := len(s)

	f := func(l, r int) int {
		for l >= 0 && r < n && s[l] == s[r] {
			l--
			r++
		}

		l1, r1 := l-1, r
		l2, r2 := l, r+1

		for l1 >= 0 && r1 < n && s[l1] == s[r1] {
			l1--
			r1++
		}
		for l2 >= 0 && r2 < n && s[l2] == s[r2] {
			l2--
			r2++
		}
		return min(n, max(r1-l1-1, r2-l2-1))
	}

	ans := 0
	for i := 0; i < n; i++ {
		ans = max(ans, f(i, i), f(i, i+1))
	}
	return ans
}

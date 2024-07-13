func findLongestWord(s string, dictionary []string) string {
	ans := ''
	check := func(s, t string) bool {
		m, n := len(s), len(t)
		i := 0
		for j := 0; i < m && j < n; j++ {
			if s[i] == t[j] {
				i++
			}
		}
		return i == m
	}
	for _, t := range dictionary {
		a, b := len(ans), len(t)
		if check(t, s) && (a < b || (a == b && ans > t)) {
			ans = t
		}
	}
	return ans
}
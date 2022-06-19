func findLongestWord(s string, dictionary []string) string {
	ans := ""
	check := func(a, b string) bool {
		m, n := len(a), len(b)
		i, j := 0, 0
		for i < m && j < n {
			if a[i] == b[j] {
				j++
			}
			i++
		}
		return j == n
	}
	for _, a := range dictionary {
		if check(s, a) && (len(ans) < len(a) || (len(ans) == len(a) && a < ans)) {
			ans = a
		}
	}
	return ans
}
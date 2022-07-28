func countSubstrings(s string, t string) int {
	m, n := len(s), len(t)
	ans := 0
	for i := range s {
		for j := range t {
			if s[i] == t[j] {
				continue
			}
			l, r := 1, 1
			for i-l >= 0 && j-l >= 0 && s[i-l] == t[j-l] {
				l++
			}
			for i+r < m && j+r < n && s[i+r] == t[j+r] {
				r++
			}
			ans += l * r
		}
	}
	return ans
}
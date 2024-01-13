func countSubstrings(s string, t string) (ans int) {
	m, n := len(s), len(t)
	for i, a := range s {
		for j, b := range t {
			if a != b {
				l, r := 0, 0
				for i > l && j > l && s[i-l-1] == t[j-l-1] {
					l++
				}
				for i+r+1 < m && j+r+1 < n && s[i+r+1] == t[j+r+1] {
					r++
				}
				ans += (l + 1) * (r + 1)
			}
		}
	}
	return
}
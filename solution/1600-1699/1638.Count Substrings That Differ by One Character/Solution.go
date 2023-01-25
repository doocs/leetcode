func countSubstrings(s string, t string) (ans int) {
	for i, a := range s {
		for j, b := range t {
			if a != b {
				l, r := 1, 1
				for i >= l && j >= l && s[i-l] == t[j-l] {
					l++
				}
				for i+r < len(s) && j+r < len(t) && s[i+r] == t[j+r] {
					r++
				}
				ans += l * r
			}
		}
	}
	return
}
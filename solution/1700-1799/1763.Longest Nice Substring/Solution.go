func longestNiceSubstring(s string) string {
	n := len(s)
	k, mx := -1, 0
	for i := 0; i < n; i++ {
		ss := map[byte]bool{}
		for j := i; j < n; j++ {
			ss[s[j]] = true
			ok := true
			for a := range ss {
				b := a ^ 32
				if !(ss[a] && ss[b]) {
					ok = false
					break
				}
			}
			if ok && mx < j-i+1 {
				mx = j - i + 1
				k = i
			}
		}
	}
	if k < 0 {
		return ""
	}
	return s[k : k+mx]
}
func longestSemiRepetitiveSubstring(s string) (ans int) {
	cnt, l := 0, 0
	for i, c := range s[1:] {
		if byte(c) == s[i] {
			cnt++
		}
		if cnt > 1 {
			if s[l] == s[l+1] {
				cnt--
			}
			l++
		}
	}
	return len(s) - l
}
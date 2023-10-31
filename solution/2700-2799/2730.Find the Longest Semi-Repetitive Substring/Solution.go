func longestSemiRepetitiveSubstring(s string) (ans int) {
	n := len(s)
	for i, j, cnt := 0, 0, 0; i < n; i++ {
		if i > 0 && s[i] == s[i-1] {
			cnt++
		}
		for cnt > 1 {
			if s[j] == s[j+1] {
				cnt--
			}
			j++
		}
		ans = max(ans, i-j+1)
	}
	return
}
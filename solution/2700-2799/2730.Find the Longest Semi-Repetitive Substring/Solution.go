func longestSemiRepetitiveSubstring(s string) (ans int) {
	ans = 1
	for i, j, cnt := 1, 0, 0; i < len(s); i++ {
		if s[i] == s[i-1] {
			cnt++
		}
		for ; cnt > 1; j++ {
			if s[j] == s[j+1] {
				cnt--
			}
		}
		ans = max(ans, i-j+1)
	}
	return
}
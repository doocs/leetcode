func countLetters(s string) (ans int) {
	i, n := 0, len(s)
	for i < n {
		j := i
		cnt := 0
		for j < n && s[j] == s[i] {
			j++
			cnt++
			ans += cnt
		}
		i = j
	}
	return
}
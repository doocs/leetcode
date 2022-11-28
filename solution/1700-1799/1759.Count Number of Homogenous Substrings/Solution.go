func countHomogenous(s string) (ans int) {
	n := len(s)
	const mod int = 1e9 + 7
	for i, j := 0, 0; i < n; i = j {
		j = i
		for j < n && s[j] == s[i] {
			j++
		}
		cnt := j - i
		ans += (1 + cnt) * cnt / 2
		ans %= mod
	}
	return
}
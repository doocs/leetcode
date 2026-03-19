func countBinarySubstrings(s string) (ans int) {
	n := len(s)
	i := 0
	pre := 0
	for i < n {
		j := i + 1
		for j < n && s[j] == s[i] {
			j++
		}
		cur := j - i
		ans += min(pre, cur)
		pre = cur
		i = j
	}
	return
}

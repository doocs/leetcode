func makeAntiPalindrome(s string) string {
	cs := []byte(s)
	sort.Slice(cs, func(i, j int) bool { return cs[i] < cs[j] })
	n := len(cs)
	m := n / 2
	if cs[m] == cs[m-1] {
		i := m
		for i < n && cs[i] == cs[i-1] {
			i++
		}
		for j := m; j < n && cs[j] == cs[n-j-1]; i, j = i+1, j+1 {
			if i >= n {
				return "-1"
			}
			cs[i], cs[j] = cs[j], cs[i]
		}
	}
	return string(cs)
}
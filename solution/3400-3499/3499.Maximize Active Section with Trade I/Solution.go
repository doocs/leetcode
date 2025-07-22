func maxActiveSectionsAfterTrade(s string) (ans int) {
	n := len(s)
	pre, mx := math.MinInt, 0

	for i := 0; i < n; {
		j := i + 1
		for j < n && s[j] == s[i] {
			j++
		}
		cur := j - i
		if s[i] == '1' {
			ans += cur
		} else {
			mx = max(mx, pre+cur)
			pre = cur
		}
		i = j
	}

	ans += mx
	return
}